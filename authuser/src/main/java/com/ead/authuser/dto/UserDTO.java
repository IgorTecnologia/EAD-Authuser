package com.ead.authuser.dto;

import com.ead.authuser.entity.*;
import com.ead.authuser.enums.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends RepresentationModel<UserDTO> implements Serializable {
    private static final long serialVersionUID = 1L;

    public interface UserView{
        public static interface RegistrationPost{}
        public static interface UserPut{}
        public static interface PasswordPut{}
        public static interface ImagePut{}
    }

    private UUID id;

    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    @NotBlank(message = "Invalid username.", groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    @Email(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @Size(min =6, max = 20, groups = UserView.PasswordPut.class)
    @NotBlank(groups =  UserView.PasswordPut.class)
    @JsonView( UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView({UserView.ImagePut.class, UserView.RegistrationPost.class})
    private String imageUrl;

    private Type type;
    private Status status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    public UserDTO(){
    }

    public UserDTO(User entity){

        id = entity.getId();
        username = entity.getUsername();
        email = entity.getEmail();
        fullName = entity.getFullName();
        phoneNumber = entity.getPhoneNumber();
        cpf = entity.getCpf();
        imageUrl = entity.getImageUrl();
        type = entity.getType();
        status = entity.getStatus();
        creationDate = entity.getCreationDate();
        lastUpdateDate = entity.getLastUpdateDate();

    }

    public UserDTO (String imageUrl, LocalDateTime lastUpdateDate){

        this.imageUrl = imageUrl;
        this.lastUpdateDate = lastUpdateDate;

    }

}
