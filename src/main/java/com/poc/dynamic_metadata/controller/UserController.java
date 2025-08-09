package com.poc.dynamic_metadata.controller;

import com.poc.dynamic_metadata.dto.UserRequest;
import com.poc.dynamic_metadata.dto.UserUpdateRequest;
import com.poc.dynamic_metadata.exception.ServiceException;
import com.poc.dynamic_metadata.model.User;
import com.poc.dynamic_metadata.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Slf4j
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
    public ResponseEntity<User> getUserByUsername(@NotNull @PathVariable String username)  {
        try{
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUid(@Valid @RequestParam String uid) {
        try{
            User user = userService.getUserByUid(uid);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }

    }

    @PutMapping("/user/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest){
        try{
            User user = userService.updateUser(userUpdateRequest);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Map<String,String>> deleteUser(@NotNull @PathVariable String username){
        try{
            log.info("Deleting user with username: {}",username);
            userService.deleteUser(username);
            return ResponseEntity.ok(Map.of("message",username+" deleted"));
        }
        catch (Exception e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
