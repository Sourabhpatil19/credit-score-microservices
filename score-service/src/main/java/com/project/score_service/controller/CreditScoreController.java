package com.project.score_service.controller;


import com.project.score_service.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-score")
@RequiredArgsConstructor
public class CreditScoreController {

    private final CreditScoreService service;

    @GetMapping("/{userId}")
    public Integer calculateScore(
            @PathVariable Long userId
    ) {
        return service.calculateCreditScore(userId);
    }
}
