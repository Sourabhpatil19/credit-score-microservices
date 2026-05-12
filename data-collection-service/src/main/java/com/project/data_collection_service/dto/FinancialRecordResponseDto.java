package com.project.data_collection_service.dto;




        import lombok.Builder;
        import lombok.Data;

        import java.math.BigDecimal;
        import java.time.LocalDate;

@Data
@Builder
public class FinancialRecordResponseDto {

    private Long recordId;

    private Long userId;

    private LocalDate transactionDate;

    private Double amount;

    private String transactionType;

    private String transactionDescription;

    private String category;
}