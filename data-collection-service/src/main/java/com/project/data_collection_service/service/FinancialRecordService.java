package com.project.data_collection_service.service;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.dto.FinancialRecordResponseDto;
import com.project.data_collection_service.entity.FinancialRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialRecordService {
    FinancialRecordResponseDto createRecord(FinancialRecordRequestDto dto);
    Page<FinancialRecordResponseDto > getByUserId(Long userId,
                                      Pageable pageable);
    List<FinancialRecordResponseDto> getAllRecords();
    Page<FinancialRecordResponseDto> getByCategory(
            String category,
            Pageable pageable
    );
    Page<FinancialRecordResponseDto> getByCategoryAndType(
            String category,
            String transactionType,
            Pageable pageable
    );

}
