package com.photoapp.users.ui.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String firstName;
    private String lastName;
    private String userId;
    private String email;
    private String password;
    private String encryptedPassword;
}
