package com.ead.authuser.services;

import com.ead.authuser.dto.*;
import com.ead.authuser.entity.*;
import com.ead.authuser.enums.*;
import com.ead.authuser.repository.*;
import com.ead.authuser.services.exception.*;
import com.ead.authuser.specification.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Transactional
@Service
public class UserServiceImpl implements UserService{

   @Autowired
   private UserRepository repository;

    @Override
    public Page<UserDTO> findAllPaged(SpecificationTemplate.UserSpec spec, Pageable pageable) {

        Page<User> page = repository.findAll(spec, pageable);
        return page.map(UserDTO::new);
    }

    @Override
    public UserDTO findById(UUID id) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(EntityNotFoundException::new);
        return new UserDTO(entity);
    }

    @Override
    public UserDTO insert(UserDTO dto) {

        if (repository.existsByUsername(dto.getUsername())) {
            throw new SignupException("Username is Already Taken!");
        } else if (repository.existsByEmail(dto.getEmail())) {
           throw new SignupException("Email is Already is Taken!");
        }

        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setType(Type.STUDENT);
        entity.setStatus(Status.ACTIVE);
        entity.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        entity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        repository.save(entity);

        return new UserDTO(entity);
    }

    @Override
    public UserDTO update(UUID id, UserDTO dto) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        entity.setFullName(dto.getFullName());
        entity.setCpf(dto.getCpf());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setType(Type.STUDENT);
        entity.setStatus(Status.ACTIVE);
        entity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        repository.save(entity);

        return new UserDTO(entity);
    }

    @Override
    public UserDTO updatePassword(UUID id, UserDTO dto) {

            Optional<User> obj = repository.findById(id);
            User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
            if (!entity.getPassword().equals(dto.getOldPassword())) {
             throw new PasswordException("Old password invalid.");
            }
            entity.setPassword(dto.getPassword());
            entity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            repository.save(entity);

            return new UserDTO(entity);
        }

    @Override
    public UserDTO updateImage(UUID id, UserDTO dto) {

        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        entity.setImageUrl(dto.getImageUrl());
        entity.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        repository.save(entity);

        return new UserDTO(entity.getImageUrl(), entity.getLastUpdateDate());
    }

    @Override
    public boolean existsByUsername(String username) {

        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {

        return repository.existsByEmail(email);

    }

    @Override
    public void deleteById(UUID id) {

            Optional<User> obj = repository.findById(id);
            if(obj.isEmpty()){
                throw new ResourceNotFoundException("Id not found: " + id);
            }
            repository.deleteById(id);
        }


    public void copyDtoToEntity(UserDTO dto, User entity){

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setCpf(dto.getCpf());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setImageUrl(dto.getImageUrl());
    }
}
