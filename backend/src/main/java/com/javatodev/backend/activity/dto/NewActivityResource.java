package com.javatodev.backend.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewActivityResource {
    private String title;
    private String description;
    private String location;
    private String activity_date;
    private String guides;
}
