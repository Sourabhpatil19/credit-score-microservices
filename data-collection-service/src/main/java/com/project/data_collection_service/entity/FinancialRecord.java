package com.project.data_collection_service.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;



@Entity
@Table(name = "financial_data")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    private Long userId;
    private LocalDate transactionDate;
    private Double amount;
    private String transactionType;
    private String transactionDescription;
    private String category;

}
