package com.javatodev.backend.activity.dto;

import com.javatodev.backend.user.entity.Guide;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResource {

    private Long id;
    private String title;
    private String description;
    private String location;
    private String activity_date;
    private String guides;
}
