package com.project.user_service.repository;

import com.project.user_service.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Page<User>findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
