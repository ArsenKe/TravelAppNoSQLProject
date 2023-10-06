package com.javatodev.backend.activity.repository;

import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.user.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    List<Activity> findAllByGuides(Guide guide);
}
