package com.javatodev.backend.activity.entity.controller;

import com.javatodev.backend.activity.dto.ActivityResource;
import com.javatodev.backend.activity.dto.NewActivityResource;
import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.activity.service.ActivityService;
import com.javatodev.backend.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ActivityController {
    private final ActivityService activityService;

    //create activity
    @PostMapping()
    public ResponseEntity<?> createActivity(@RequestBody NewActivityResource activity) {
        Activity activity1= activityService.createActivity(activity);
        if (activity!= null){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        throw new UserNotFoundException("Activity creation failure.");
    }

    //retrieve user's activity
    @GetMapping(value = "/{userId}")
    public ResponseEntity<List<ActivityResource>> getAllActivitiesByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(activityService.getAllActivitiesByUserId(userId));
    }

    //retrieve all activities
    @GetMapping()
    public ResponseEntity<List<ActivityResource>> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

}
