package com.javatodev.backend.nosql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Document(collection = "rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingNoSql {
    @Id
    private Long ratingId;

    private String comment;

    private String safety;

    private String quality;

    private Date ratingDate=new Date();

    private Long activityId;

    private Long touristId;

}
