package com.photoapp.users.exceptions;

public class UserServiceException extends RuntimeException {

    public UserServiceException(String message){
        super(message);
    }
}
