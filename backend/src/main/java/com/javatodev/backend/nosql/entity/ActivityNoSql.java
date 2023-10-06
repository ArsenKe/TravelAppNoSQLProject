package com.javatodev.backend.nosql.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Document(collection = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityNoSql {
    @Id
    private Long id;

    @NotNull
    private String title;

    private String description;

    private String location;

    private String activityDate;

    private Date listingDate;

    private Long guideId;

}
