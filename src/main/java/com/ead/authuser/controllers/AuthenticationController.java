package com.ead.authuser.controllers;

import com.ead.authuser.dto.UserDTO;
import com.ead.authuser.services.impl.UserServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3700)
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping(value = "/signup")
    public ResponseEntity<UserDTO> register(@RequestBody
                                            @Validated(UserDTO.UserView.RegistrationPost.class)
                                            @JsonView(UserDTO.UserView.RegistrationPost.class) UserDTO dto) {

        dto = service.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping(value = "/")
    public String index() {

        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");

        return "Logging Spring Boot...";
    }
}