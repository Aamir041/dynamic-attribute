package com.poc.dynamic_metadata.controller;

import com.poc.dynamic_metadata.dto.UserRequest;
import com.poc.dynamic_metadata.dto.UserUpdateRequest;
import com.poc.dynamic_metadata.model.User;
import com.poc.dynamic_metadata.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/health")
    public Map<String,String> health()  {
        return Map.of("status","up");
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRequest userRequest){
        User user = userService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUserByUsername(@Valid @PathVariable String username){
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUid(@Valid @RequestParam String uid) throws Exception {
        User user = userService.getUserByUid(uid);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        User user = userService.updateUser(userUpdateRequest);
        return ResponseEntity.ok(user);
    }

}
