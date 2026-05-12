package com.project.data_collection_service.controller;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.entity.FinancialRecord;
import com.project.data_collection_service.service.FinancialRecordService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financial-records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService service;

    @PostMapping
    public FinancialRecord create(
            @Valid @RequestBody FinancialRecordRequestDto dto) {

        return service.createRecord(dto);
    }

    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getByUserId(
            @PathVariable Long userId) {

        return service.getByUserId(userId);
    }
}