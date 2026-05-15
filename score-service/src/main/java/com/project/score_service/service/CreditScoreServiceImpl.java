package com.project.score_service.service;

import com.project.score_service.client.DataCollectionFeignClient;
import com.project.score_service.dto.CreditScoreResponseDto;
import com.project.score_service.dto.FinancialRecordResponseDto;
import com.project.score_service.entity.CreditScore;
import com.project.score_service.expection.CreditScoreNotFound;
import com.project.score_service.repository.CreditScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditScoreServiceImpl
        implements CreditScoreService {

    private final CreditScoreRepository repository;
    private final DataCollectionFeignClient financialRecordClient;

    @Override
    public CreditScoreResponseDto calculateScore(Long userId) {

        List<FinancialRecordResponseDto> records =
                financialRecordClient.getRecordsByUserId(userId);

        int score = 600;

        BigDecimal totalCredit = BigDecimal.ZERO;
        BigDecimal totalDebit = BigDecimal.ZERO;

        for (FinancialRecordResponseDto record : records) {

            if ("CREDIT".equalsIgnoreCase(
                    record.getTransactionType())) {

                totalCredit = totalCredit.add(record.getAmount());
            }

            if ("DEBIT".equalsIgnoreCase(
                    record.getTransactionType())) {

                totalDebit = totalDebit.add(record.getAmount());
            }
        }

     if (totalCredit.compareTo(totalDebit) > 0) {
            score += 100;
        }

        if (totalDebit.compareTo(
                BigDecimal.valueOf(100000)) > 0) {
            score -= 50;
        }

        if (records.size() > 10) {
            score += 30;
        }

        boolean eligible = score >= 650;

        String riskLevel;

        if (score >= 750) {
            riskLevel = "LOW";
        } else if (score >= 650) {
            riskLevel = "MEDIUM";
        } else {
            riskLevel = "HIGH";
        }

        CreditScore creditScore =
                CreditScore.builder()
                        .userId(userId)
                        .creditScore(score)
                        .eligible(eligible)
                        .riskLevel(riskLevel)
                        .createdAt(LocalDateTime.now())
                        .build();

        repository.save(creditScore);

        return mapToDto(creditScore);
    }

    @Override
    public CreditScoreResponseDto getScore(Long userId) {

        CreditScore creditScore =
                repository.findByUserId(userId)
                        .orElseThrow(() ->
                                new CreditScoreNotFound(
                                        "Credit score not found"));
        log.info("Calculating credit score for userId: {}", userId);
        return mapToDto(creditScore);
    }

    private CreditScoreResponseDto mapToDto(
            CreditScore creditScore) {
        log.info("Credit score calculated successfully");
        return CreditScoreResponseDto.builder()
                .userId(creditScore.getUserId())
                .creditScore(creditScore.getCreditScore())
                .eligible(creditScore.getEligible())
                .riskLevel(creditScore.getRiskLevel())
                .createdAt(creditScore.getCreatedAt())
                .build();
    }
}
