package com.photoapp.users.userservice;


import com.photoapp.users.shared.Utils;
import com.photoapp.users.ui.data.UserEntity;
import com.photoapp.users.ui.data.UsersRepository;
import com.photoapp.users.ui.model.request.UserDetailsRequestModel;
import com.photoapp.users.ui.model.response.UserDto;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    Utils utils;

    public UsersServiceImpl(){}

    @Autowired
    public UsersServiceImpl(Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        /*UserDto user = new UserDto();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());*/

        String userId = utils.generateUserId();
        userDetails.setUserId(userId);
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        usersRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }
}
