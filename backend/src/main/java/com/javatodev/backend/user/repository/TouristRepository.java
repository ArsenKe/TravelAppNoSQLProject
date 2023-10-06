package com.javatodev.backend.user.repository;

import com.javatodev.backend.user.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist,Long> {
}
