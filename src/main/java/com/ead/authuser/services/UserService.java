package com.ead.authuser.services;

import com.ead.authuser.dto.UserDTO;
import com.ead.authuser.specification.SpecificationTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    Page<UserDTO> findAllPaged(SpecificationTemplate.UserSpec spec, Pageable pageable);

    UserDTO findById(UUID id);

    UserDTO insert(UserDTO dto);

    UserDTO update(UUID id, UserDTO dto);

    void updatePassword(UUID id, UserDTO dto);

    UserDTO updateImage(UUID id, UserDTO dto);

    void deleteById(UUID id);
}
