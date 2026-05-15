package com.project.data_collection_service.repository;

import com.project.data_collection_service.entity.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinancialRecordRepository
        extends JpaRepository<FinancialRecord, Long> {

    Page<FinancialRecord> findByUserId(Long userId, Pageable pageable);
    Page<FinancialRecord> findByCategory(
            String category,
            Pageable pageable
    );
    Page<FinancialRecord> findByCategoryAndTransactionType(
            String category,
            String transactionType,
            Pageable pageable
    );
    List<FinancialRecord> findByUserId(Long userId);
}
