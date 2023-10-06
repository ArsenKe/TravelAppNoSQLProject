package com.javatodev.backend.activity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javatodev.backend.booking.entity.Booking;
import com.javatodev.backend.rating.entity.Rating;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.entity.Tourist;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "activity_date")
    private String activityDate;

    @Column(name = "listing_date")
    private Date listingDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guides;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")
    private Set<Booking> bookings = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();


}
