package com.javatodev.backend.activity.service;

import com.javatodev.backend.activity.dto.ActivityResource;
import com.javatodev.backend.activity.dto.NewActivityResource;
import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.activity.repository.ActivityRepository;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.exception.UserNotFoundException;
import com.javatodev.backend.user.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final GuideRepository guideRepository;

    //create activity
    public Activity createActivity(NewActivityResource activityResource) {
        //check the given guideId correct
        Optional<Guide> optionalGuide = guideRepository.findById(Long.valueOf(activityResource.getGuides()));

        //user not found in the database
        if (!optionalGuide.isPresent()) {
            throw new UserNotFoundException("User not found in this id " + optionalGuide.get().getId());
        }
        //user found in the database
        //set the activity
        Activity activity = new Activity();
        activity.setTitle(activityResource.getTitle());
        activity.setDescription(activityResource.getDescription());
        activity.setLocation(activityResource.getLocation());
        activity.setActivityDate(activityResource.getActivity_date());
        activity.setListingDate(new Date());
        activity.setGuides(optionalGuide.get());
        //save the activity record to database
        return activityRepository.save(activity);
    }

    //retrieve user's activity
    public List<ActivityResource> getAllActivitiesByUserId(String userId) {
        //check the given guideId correct
        Optional<Guide> optionalGuide = guideRepository.findById(Long.valueOf(userId));

        //user not found in the database
        if (!optionalGuide.isPresent()) {
            throw new UserNotFoundException("User not found in this id " + userId);
        }
        //user found in the database
        //Map activity list to activity resource list
        List<ActivityResource> activities = activityRepository.findAllByGuides(optionalGuide.get()).stream()
                .map(activity -> toEntity(activity)).collect(Collectors.toList());
        return activities;
    }

    //retrieve all activities
    public List<ActivityResource> getAllActivities() {
        //Map activity list to activity resource list
        List<ActivityResource> activities = activityRepository.findAll().stream()
                .map(activity -> toEntity(activity)).collect(Collectors.toList());
        return activities;
    }

    //Activity to Activity resource
    private ActivityResource toEntity(Activity activity) {
        ActivityResource resource = new ActivityResource();
        resource.setId(activity.getId());
        resource.setTitle(activity.getTitle());
        resource.setDescription(activity.getDescription());
        resource.setLocation(activity.getLocation());
        resource.setActivity_date(activity.getActivityDate());
        return resource;
    }
}
