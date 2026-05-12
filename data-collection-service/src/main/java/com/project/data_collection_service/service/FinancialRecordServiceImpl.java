package com.project.datacollectionservice.service.impl;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.entity.FinancialRecord;
import com.project.data_collection_service.repository.FinancialRecordRepository;
import com.project.data_collection_service.service.FinancialRecordService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordServiceImpl
        implements FinancialRecordService {

    private final FinancialRecordRepository repository;

    @Override
    public FinancialRecord createRecord(FinancialRecordRequestDto dto) {

        FinancialRecord record = FinancialRecord.builder()
                .userId(dto.getUserId())
                .transactionDate(dto.getTransactionDate())
                .amount(dto.getAmount())
                .transactionType(dto.getTransactionType())
                .transactionDescription(dto.getTransactionDescription())
                .category(dto.getCategory())
                .build();

        return repository.save(record);
    }



    @Override
    public List<FinancialRecord> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}