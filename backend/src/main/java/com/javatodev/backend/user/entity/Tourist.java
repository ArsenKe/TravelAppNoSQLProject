package com.javatodev.backend.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.booking.entity.Booking;
import com.javatodev.backend.rating.entity.Rating;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tourist")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Tourist {
    @Id
    private Long id;

    @Column(name = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;

    @Column(name = "living_location")
    private String livingLocation;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "tourist")
    private Set<Booking> bookings =new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "tourist", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Rating> ratings=new HashSet<>();

}
