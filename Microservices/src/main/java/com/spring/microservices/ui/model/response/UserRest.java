package com.spring.microservices.ui.model.response;

import lombok.Data;

@Data
public class UserRest {

    private String firstName;
    private String lastName;
    private String userId;
    private String email;

}
