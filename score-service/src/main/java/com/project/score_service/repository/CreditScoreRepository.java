package com.project.score_service.repository;

import com.project.score_service.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository
        extends JpaRepository<CreditScore, Long> {

    Optional<CreditScore> findByUserId(Long userId);
}