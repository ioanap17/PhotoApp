package com.photoapp.users.ui.controllers;

import com.photoapp.users.ui.model.CreateUserResponseModel;
import com.photoapp.users.ui.model.request.UpdateUserDetailsRequestModel;
import com.photoapp.users.ui.model.request.UserDetailsRequestModel;
import com.photoapp.users.ui.model.response.UserDto;
import com.photoapp.users.userservice.UsersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private Environment environment;

    Map<String, UserDto> users;

    @Autowired
    UsersService userService;

    @GetMapping("/status/check")
    public String status(){

        return "Working on port " + environment.getProperty("local.server.port");
    }



    @GetMapping
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort) {

        return "Get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUser(@PathVariable String userId) {

        /*if (true){
            throw new UserServiceException("ERROR!!! UserServiceException");
        }*/

        if(users.containsKey(userId)) {
            return new ResponseEntity(users.get(userId), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto user = modelMapper.map(userDetails, UserDto.class);
        userService.createUser(user);

        CreateUserResponseModel returnValue = modelMapper.map(user, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @PutMapping(path = "/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserDto updateUser(@PathVariable String userId,
                              @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {

        UserDto storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        users.remove(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
