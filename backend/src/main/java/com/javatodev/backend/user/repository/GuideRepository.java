package com.javatodev.backend.user.repository;

import com.javatodev.backend.user.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuideRepository extends JpaRepository<Guide,Long> {

}
