package com.project.user_service.auth.controller;

import com.project.user_service.auth.dto.LoginRequestDto;
import com.project.user_service.auth.dto.LoginResponseDto;
import com.project.user_service.auth.dto.RefreshTokenRequestDto;
import com.project.user_service.auth.dto.RefreshTokenResponseDto;
import com.project.user_service.auth.service.AuthService;
import com.project.user_service.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto dto){
        LoginResponseDto response = authService.authenticate(dto);

        return ResponseEntity.ok( ApiResponse.<LoginResponseDto>builder()
                .success(true)
                .message("Login successful")
                .data(response)
                .build());
    }
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshTokenResponseDto>> refreshToken( @Valid @RequestBody RefreshTokenRequestDto dto){
        RefreshTokenResponseDto response =
               authService.refreshAccessToken(dto);


        return ResponseEntity.ok(
                ApiResponse.<RefreshTokenResponseDto>builder()
                        .success(true)
                        .message("Access token refreshed successfully")
                        .data(response)
                        .build()
        );

    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Object>> logout(
            @RequestParam String email
    ) {

        authService.logout(email);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Logged out successfully")
                        .data(null)
                        .build()
        );
    }
}
