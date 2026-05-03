package com.project.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class UserRequestDto {
@NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
    @Email(message = " Invalid email ")
    private String email;
}
