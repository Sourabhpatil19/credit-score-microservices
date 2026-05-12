package com.project.data_collection_service.service;

import com.project.data_collection_service.dto.FinancialRecordRequestDto;
import com.project.data_collection_service.entity.FinancialRecord;

import java.util.List;

public interface FinancialRecordService {
    FinancialRecord createRecord(FinancialRecordRequestDto dto);
    List<FinancialRecord> getByUserId(Long userId);

}
