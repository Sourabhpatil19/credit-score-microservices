package com.project.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class UserRequestDto {
@NotBlank(message = "username is required")
@Size(min = 3, max = 20,
        message = "Username must be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "Password must contain uppercase, lowercase, number and minimum 8 characters"
    )
    private String password;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")

    private String email;
}
