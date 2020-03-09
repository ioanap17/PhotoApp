package com.photoapp.users.userservice;


import com.photoapp.users.ui.model.request.UserDetailsRequestModel;
import com.photoapp.users.ui.model.response.UserDto;

public interface UsersService {

    UserDto createUser(UserDto userDetails);
}
