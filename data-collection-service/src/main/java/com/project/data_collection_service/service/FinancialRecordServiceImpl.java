package com.project.data_collection_service.service;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.dto.FinancialRecordResponseDto;
import com.project.data_collection_service.entity.FinancialRecord;
import com.project.data_collection_service.expection.ResourceNotFoundException;
import com.project.data_collection_service.repository.FinancialRecordRepository;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordServiceImpl
        implements FinancialRecordService {

    private final FinancialRecordRepository repository;
    private static final Logger logger =
            LogManager.getLogger(FinancialRecordServiceImpl.class);

    @Override
    public FinancialRecordResponseDto createRecord(FinancialRecordRequestDto dto) {


        FinancialRecord record = new FinancialRecord();

        record.setUserId(dto.getUserId());
        record.setTransactionDate(dto.getTransactionDate());
        record.setAmount(dto.getAmount());
        record.setTransactionType(dto.getTransactionType());
        record.setTransactionDescription(dto.getTransactionDescription());
        record.setCategory(dto.getCategory());

        FinancialRecord savedRecord = repository.save(record);
        logger.info("Creating financial record");
        return mapToDto(savedRecord);

    }

    @Override
    public Page<FinancialRecordResponseDto> getByUserId(Long userId, Pageable pageable) {

       Page <FinancialRecord> record = repository.findByUserId(userId, pageable);
       if(record.isEmpty()){
           logger.error("\"Exception occurred for finding the user \"");
           throw new ResourceNotFoundException("Data is not present for userId : {}",userId);
       }
        logger.info(
                "Fetching records for userId: {}",
                userId);
        return  record.map(this::mapToDto);
    }
    @Override
    public List<FinancialRecordResponseDto> getAllRecords() {

        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }



    private FinancialRecordResponseDto mapToDto(FinancialRecord record) {

        return FinancialRecordResponseDto.builder()
                .recordId(record.getRecordId())
                .userId(record.getUserId())
                .transactionDate(record.getTransactionDate())
                .amount(record.getAmount())
                .transactionType(record.getTransactionType())
                .transactionDescription(record.getTransactionDescription())
                .category(record.getCategory())
                .build();
    }
    @Override
    public Page<FinancialRecordResponseDto> getByCategory(
            String category,
            Pageable pageable
    ) {

        Page<FinancialRecord> records =
                repository.findByCategory(category, pageable);

        return records.map(this::mapToDto);
    }
    @Override
    public Page<FinancialRecordResponseDto> getByCategoryAndType(
            String category,
            String transactionType,
            Pageable pageable
    ) {

        Page<FinancialRecord> records =
                repository.findByCategoryAndTransactionType(
                        category,
                        transactionType,
                        pageable
                );

        return records.map(this::mapToDto);
    }
    @Override
    public List<FinancialRecordResponseDto> getByUserId(Long userId) {

        logger.info("Fetching records for userId: {}", userId);

        List<FinancialRecord> records =
                repository.findByUserId(userId);

        return records.stream()
                .map(this::mapToDto)
                .toList();
    }
}