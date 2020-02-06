package com.spring.microservices.userservice.impl;

import com.spring.microservices.shared.Utils;
import com.spring.microservices.ui.model.request.UserDetailsRequestModel;
import com.spring.microservices.ui.model.response.UserRest;
import com.spring.microservices.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;

    Utils utils;

    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(Utils utils){
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest user = new UserRest();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        String userId = utils.generateUserId();
        user.setUserId(userId);

        if(users == null)
            users = new HashMap<>();
        users.put(userId, user);

        return user;
    }
}
