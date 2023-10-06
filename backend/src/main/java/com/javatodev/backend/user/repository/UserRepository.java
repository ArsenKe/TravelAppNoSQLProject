package com.javatodev.backend.user.repository;

import com.javatodev.backend.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);

    Page<User> findAll(Pageable pageable);

    boolean existsByUsernameOrEmail(String username, String email);
}
