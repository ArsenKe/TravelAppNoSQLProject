package com.javatodev.backend.rating.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.entity.Tourist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @Column(name = "rating_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "safety")
    private String safety;

    @Column(name = "quality")
    private String quality;

    @Column(name = "rating_date")
    private Date ratingDate=new Date();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "tourist_id", nullable = false)
    private Tourist tourist;
}
