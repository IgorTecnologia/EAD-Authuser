package com.ead.authuser.resources;

import com.ead.authuser.dto.*;
import com.ead.authuser.services.*;
import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    UserService service;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDTO> registerUser(@Validated(UserDTO.UserView.RegistrationPost.class)
                                                    @JsonView(UserDTO.UserView.RegistrationPost.class)
                                                    @RequestBody UserDTO dto) {

        dto = service.insert(dto);

        return ResponseEntity.ok().body(dto);
    }
}

