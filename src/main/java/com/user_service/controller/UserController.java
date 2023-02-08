package com.user_service.controller;

import com.user_service.dto.UserRequest;
import com.user_service.model.User;
import com.user_service.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //POST API to REGISTER USER
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    // GET API to GET USER
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserById(Long.parseLong(userId)), HttpStatus.OK);
    }
    //PUT API to UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId,
                                           @RequestBody UserRequest userRequest){
        return  new ResponseEntity<>(userService.updateUser(Long.parseLong(userId), userRequest),HttpStatus.OK);
    }

    // DELETE API to DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId){
        userService.deleteUser(Long.parseLong(userId));
        return new ResponseEntity<>(Void.class, HttpStatus.OK);
    }
}
