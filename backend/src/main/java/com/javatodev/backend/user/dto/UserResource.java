package com.javatodev.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResource {
    private String id;
    private String username;
    private String email;
    private String bio;
    private String userType;
}
