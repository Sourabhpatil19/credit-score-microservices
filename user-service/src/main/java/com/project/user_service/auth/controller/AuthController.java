package com.project.user_service.auth.controller;

import com.project.user_service.common.ApiResponse;
import com.project.user_service.auth.dto.LoginRequestDto;
import com.project.user_service.entity.User;
import com.project.user_service.expection.ResourceNotFoundException;
import com.project.user_service.repository.UserRepository;
import com.project.user_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<Map<String, String>>> authenticate(@RequestBody LoginRequestDto request){
        User user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(()-> new ResourceNotFoundException("Invalid Credentials"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        return ResponseEntity.ok((new ApiResponse<>(true,"Login successful",Map.of("token",token))));
    }
}
