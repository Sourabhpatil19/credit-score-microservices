package com.project.score_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;



@Data
public class FinancialRecordResponseDto {

    private Long id;
    private Long userId;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String transactionType;
    private String category;
}