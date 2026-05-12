package com.project.data_collection_service.controller;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.dto.FinancialRecordResponseDto;
import com.project.data_collection_service.entity.FinancialRecord;


import com.project.data_collection_service.service.FinancialRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Financial Record APIs")
@RestController
@RequestMapping("/financial-records")
@RequiredArgsConstructor
public class FinancialRecordController {

    private final FinancialRecordService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create financial record")
    public FinancialRecordResponseDto createRecord(
            @Valid @RequestBody FinancialRecordRequestDto requestDto
    ) {

        return service.createRecord(requestDto);
    }
    @GetMapping
    public List<FinancialRecordResponseDto> getAllRecords() {

        return service.getAllRecords();
    }
    @GetMapping("/{userId}")
    public Page<FinancialRecordResponseDto> getRecords(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getByUserId(
                userId,
                PageRequest.of(page, size));
    }
}