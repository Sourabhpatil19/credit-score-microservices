package com.project.score_service.client;


import com.project.score_service.dto.FinancialRecordResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "data-collection-service")
public interface DataCollectionFeignClient {

    @GetMapping("financial-records/user/{userId}")
    List<FinancialRecordResponseDto> getRecordsByUserId(
            @PathVariable Long userId
    );
}