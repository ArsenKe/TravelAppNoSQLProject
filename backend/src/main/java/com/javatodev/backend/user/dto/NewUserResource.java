package com.javatodev.backend.user.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserResource {

    @NotNull
    private String username;

    @NotNull
    private String email;
    private String password;
    private String bio;
    private String userType;

    // tourist other details
    private String birthday=null;
    private String living_location=null;

}
