package com.javatodev.backend.nosql.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNoSql {

    @Id
    private Long id;

    @Indexed(name = "username_index_unique", unique = true)
    @NotNull
    private String username;

    @Indexed(name = "email_index_unique", unique = true)
    @NotNull
    private String email;

    private Date registrationDate = new Date();

    private String password;

    private String bio;

    private String userType;

    private String birthday;

    private String livingLocation;

    private Double averageRating = 0.0;

    private int countOfTours = 0;

}
