package com.javatodev.backend.user.controller;

import com.javatodev.backend.user.dto.LoginResource;
import com.javatodev.backend.user.dto.NewUserResource;
import com.javatodev.backend.user.dto.UserResource;
import com.javatodev.backend.user.entity.User;
import com.javatodev.backend.user.exception.UserNotFoundException;
import com.javatodev.backend.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {
    private final UserService userService;

    //create user -self registration
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody NewUserResource newUserResource){
        Object user=userService.createUser(newUserResource);
        if (user!= null){
                //user created success
                return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        //user created unsuccess
        throw new UserNotFoundException("User registration failure.");
    }

    //user login the system
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginResource loginResource){
        return ResponseEntity.ok(userService.loginUser(loginResource));
    }

}
