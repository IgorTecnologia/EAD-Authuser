package com.ead.authuser.services;

import com.ead.authuser.dto.*;
import com.ead.authuser.entity.*;
import com.ead.authuser.specification.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import java.util.*;

public interface UserService {

    Page<UserDTO> findAllPaged(SpecificationTemplate.UserSpec spec, Pageable pageable);

    public UserDTO findById(UUID id);

    public UserDTO insert(UserDTO dto);

    public UserDTO update(UUID id, UserDTO dto);

    public void deleteById(UUID id);

    public UserDTO updatePassword(UUID id, UserDTO dto);

    public UserDTO updateImage(UUID id, UserDTO dto);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
