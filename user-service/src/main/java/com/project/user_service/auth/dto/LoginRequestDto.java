package com.project.user_service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "Email is Required")
    private String email;
    @NotBlank(message = "password is Required")
    private String password;
}
