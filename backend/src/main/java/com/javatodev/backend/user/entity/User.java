package com.javatodev.backend.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javatodev.backend.commons.UserType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "register_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    @Column(unique=true)
    private String email;

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "registration_date")
    private Date registrationDate=new Date();

    private String bio;

    @Column(name = "user_type")
    private String userType;

}
