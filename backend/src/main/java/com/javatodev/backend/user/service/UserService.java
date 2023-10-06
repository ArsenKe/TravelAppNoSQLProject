package com.javatodev.backend.user.service;

import com.javatodev.backend.commons.HashMD5;
import com.javatodev.backend.commons.UserType;
import com.javatodev.backend.user.dto.LoginResource;
import com.javatodev.backend.user.dto.NewUserResource;
import com.javatodev.backend.user.dto.UserResource;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.entity.Tourist;
import com.javatodev.backend.user.entity.User;
import com.javatodev.backend.user.exception.UserNotFoundException;
import com.javatodev.backend.user.repository.GuideRepository;
import com.javatodev.backend.user.repository.TouristRepository;
import com.javatodev.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;

    //create user -self registration
    public Object createUser(NewUserResource newUserResource) {

        if (userRepository.existsByUsernameOrEmail(newUserResource.getUsername(), newUserResource.getEmail())) {

            throw new UserNotFoundException("User already exists.");
        }

        String pass = "";
        try {
            //hashing given password
            pass = new HashMD5().giveHash(newUserResource.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //save common details of user
        User persistUser = userRepository.save(toResource(newUserResource, pass));//created user

        if (persistUser != null) {
            //after save user record
            //check the user is Guide or Tourist
            if (persistUser.getUserType().equals("Guide")) {
                Guide guide = new Guide();
                guide.setId(persistUser.getId());
                //save the guide details
                guideRepository.save(guide);
            } else if (persistUser.getUserType().equals("Tourist")) {
                Tourist tourist = new Tourist();
                tourist.setId(persistUser.getId());
                tourist.setBirthday(newUserResource.getBirthday());
                tourist.setLivingLocation(newUserResource.getLiving_location());
                //save the Tourist details
                touristRepository.save(tourist);
            }
            return persistUser;
        }
        return null;
    }

    //map the NewUserResource to user (create user object)
    private User toResource(NewUserResource newUserResource, String password) {
        User userResource = new User();
        userResource.setUsername(newUserResource.getUsername());
        userResource.setEmail(newUserResource.getEmail());
        userResource.setPassword(password);
        userResource.setBio(newUserResource.getBio());
        userResource.setUserType(newUserResource.getUserType());
        return userResource;
    }


    //login user
    public UserResource loginUser(LoginResource loginResource) {
        String password = "";
        try {
            //hashing given password
            password = new HashMD5().giveHash(loginResource.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //check the entered userName and password valid or not.
        Optional<User> user = userRepository.findByUsernameAndPassword(loginResource.getUsername(), password);
        if (user.isPresent()) {
            //valid user found
            UserResource resource = new UserResource();
            resource.setId(user.get().getId().toString());
            resource.setUsername(user.get().getUsername());
            resource.setEmail(user.get().getEmail());
            resource.setBio(user.get().getBio());
            resource.setUserType(user.get().getUserType());
            return resource;
        } else {
            //user not found
            throw new UserNotFoundException("Entered UserName or Password Invalid.");
        }
    }


}
