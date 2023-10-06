package com.javatodev.backend.nosql.entity;

import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.user.entity.Tourist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingNoSql {
    @Id
    private Long bookingId;

    private String bookingDate;

    private String reservationDate;

    private Long touristId;

    private Long activityId;
}
