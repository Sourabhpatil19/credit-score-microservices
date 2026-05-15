package com.project.score_service.service;



import com.project.score_service.client.DataCollectionFeignClient;
import com.project.score_service.dto.CreditScoreResponseDto;
import com.project.score_service.dto.FinancialRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CreditScoreService {

    CreditScoreResponseDto calculateScore(Long userId);

    CreditScoreResponseDto getScore(Long userId);
}

