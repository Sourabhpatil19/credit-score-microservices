package com.project.score_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditScoreResponseDto {

    private Long userId;

    private Integer creditScore;

    private Boolean eligible;

    private String riskLevel;

    private LocalDateTime createdAt;
}
