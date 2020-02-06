package com.spring.microservices.userservice;

import com.spring.microservices.ui.model.request.UserDetailsRequestModel;
import com.spring.microservices.ui.model.response.UserRest;

public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetails);
}
