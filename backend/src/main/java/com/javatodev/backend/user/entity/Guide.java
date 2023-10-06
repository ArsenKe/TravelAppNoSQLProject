package com.javatodev.backend.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javatodev.backend.activity.entity.Activity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guides")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guide{
    @Id
    private Long id;

    @Column(name = "average_rating")
    private Double averageRating=0.0;

    @Column(name = "count_of_tours")
    private int countOfTours=0;

    @JsonManagedReference
    @OneToMany(mappedBy = "guides", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Activity> activities=new HashSet<>();
}
