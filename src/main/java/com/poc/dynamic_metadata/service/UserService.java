package com.poc.dynamic_metadata.service;

import com.poc.dynamic_metadata.dto.UserRequest;
import com.poc.dynamic_metadata.dto.UserUpdateRequest;
import com.poc.dynamic_metadata.exception.ResourceNotFound;
import com.poc.dynamic_metadata.model.User;
import com.poc.dynamic_metadata.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User addUser(UserRequest userRequest){
        log.info("User Request : {}",userRequest);
        User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        user.setUsername(userRequest.getUsername().toLowerCase());
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        log.info("Getting user info for username : {}",username);
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFound("User with username "+username+" not found"));

    }

    public User getUserByUid(String uid)  {
        log.info("Getting user info for uid: {}",uid);
        return userRepository
                .findById(uid)
                .orElseThrow(() -> new ResourceNotFound("User with uid "+uid+" not found"));
    }

    public User updateUser(UserUpdateRequest userUpdateRequest){
        String username = userUpdateRequest.getUsername();
        String uid = userUpdateRequest.getUid();

        User user = userRepository
                .findByUidAndUsername(uid, username)
                .orElseThrow(
                        () -> new ResourceNotFound("User with username "+username+", uid "+uid+" not found")
                );

        user.setUserMetadata(userUpdateRequest.getUserMetadata());
        return userRepository.save(user);
    }

    public void deleteUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFound("User with username "+username+" not found"));
        userRepository.delete(user);
    }

}
