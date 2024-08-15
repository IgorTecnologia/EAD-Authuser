package com.ead.authuser.resources;

import com.ead.authuser.dto.*;
import com.ead.authuser.entity.*;
import com.ead.authuser.services.*;
import com.ead.authuser.specification.*;
import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAllPaged(SpecificationTemplate.UserSpec spec, Pageable pageable){

        Page<UserDTO> page = service.findAllPaged(spec, pageable);
        if(!page.isEmpty()){
            for(UserDTO dto : page.toList()){
                dto.add(linkTo(methodOn(UserResource.class).findById(dto.getId())).withSelfRel());
            }
        }
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id){

        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert (@RequestBody
                                               @Validated(UserDTO.UserView.RegistrationPost.class)
                                               @JsonView(UserDTO.UserView.RegistrationPost.class)
                                                UserDTO dto){
        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable(value = "id") UUID id,
                                            @Validated(UserDTO.UserView.UserPut.class)
                                            @RequestBody @JsonView({UserDTO.UserView.UserPut.class}) UserDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable UUID id,
                                                    @Validated(UserDTO.UserView.PasswordPut.class)
                                                    @JsonView(UserDTO.UserView.PasswordPut.class) @RequestBody UserDTO dto){
        dto = service.updatePassword(id, dto);
        return ResponseEntity.ok().body("Password updated successfully!");
    }

        @PutMapping(value = "/{id}/image")
        public ResponseEntity<UserDTO> updateImage(@PathVariable UUID id,
                                                   @Validated(UserDTO.UserView.ImagePut.class)
                                                   @JsonView(UserDTO.UserView.ImagePut.class)
                                                   @RequestBody UserDTO dto){

        dto = service.updateImage(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable UUID id){

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }
}
