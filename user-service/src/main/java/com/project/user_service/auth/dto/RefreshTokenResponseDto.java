package com.project.user_service.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenResponseDto {
    private String accessToken;
}
