package com.project.score_service.service;



import com.project.score_service.client.DataCollectionFeignClient;
import com.project.score_service.dto.FinancialRecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditScoreService {

    private final DataCollectionFeignClient feignClient;

    public Integer calculateCreditScore(Long userId) {

        List<FinancialRecordResponseDto> records =
                feignClient.getRecordsByUserId(userId);

        int score = 600;

        if(records.size() > 5) {
            score += 50;
        }

        return score;
    }
}
