package com.project.data_collection_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;


@Data
public class FinancialRecordRequestDto {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Transaction date is required")
    private LocalDate transactionDate;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotBlank(message = "Transaction type is required")
    private String transactionType;
    private String transactionDescription;

    private String category;

}
