package com.project.score_service.controller;


import com.project.score_service.dto.CreditScoreResponseDto;
import com.project.score_service.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-score")
@RequiredArgsConstructor
public class CreditScoreController {

    private final CreditScoreService service;



    @PostMapping("/calculate/{userId}")
    public ResponseEntity<CreditScoreResponseDto>
    calculateScore(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                service.calculateScore(userId)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CreditScoreResponseDto>
    getScore(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                service.getScore(userId)
        );
    }
}
