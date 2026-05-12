package com.project.data_collection_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FinancialRecordRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private Double amount;

    @NotBlank
    private String transactionType;

    private String transactionDescription;

    private String category;
}
