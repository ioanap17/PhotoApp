package com.photoapp.users.ui.model;

import lombok.Data;

@Data
public class CreateUserResponseModel {

    private String firstName;
    private String lastName;
    private String userId;
    private String email;
}
