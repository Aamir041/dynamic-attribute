package com.poc.dynamic_metadata.service;

import com.poc.dynamic_metadata.dto.UserRequest;
import com.poc.dynamic_metadata.dto.UserUpdateRequest;
import com.poc.dynamic_metadata.model.User;
import com.poc.dynamic_metadata.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

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

    public User getUserByUsername(String username){
        log.info("Getting user info for username : {}",username);
        return userRepository.findByUsername(username);
    }

    public User getUserByUid(String uid) throws Exception {
        log.info("Getting user info for uid: {}",uid);
        Optional<User> userOptional = userRepository.findById(uid);
        if(userOptional.isPresent()){
            return userOptional.get();
        }
        else{
            throw new Exception("User with uid "+uid+" does not exist");
        }
    }

    public User updateUser(UserUpdateRequest userUpdateRequest){
        Optional<User> userOptional = userRepository.findById(userUpdateRequest.getUid());
        User userInfo = userOptional.get();
        if(!userInfo.getUsername().equalsIgnoreCase(userUpdateRequest.getUsername())){
            if(ObjectUtils.isEmpty(userRepository.findByUsername(userUpdateRequest.getUsername()))){
                userInfo.setUsername(userUpdateRequest.getUsername());
            }
        }
        userInfo.setUserMetadata(userUpdateRequest.getUserMetadata());
        return userRepository.save(userInfo);
    }

}
