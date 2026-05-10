package com.project.user_service.auth.service;

import com.project.user_service.auth.dto.LoginRequestDto;
import com.project.user_service.auth.dto.LoginResponseDto;

import com.project.user_service.auth.dto.RefreshTokenRequestDto;
import com.project.user_service.auth.dto.RefreshTokenResponseDto;
import com.project.user_service.auth.entity.RefreshToken;
import com.project.user_service.entity.User;
import com.project.user_service.expection.ResourceNotFoundException;
import com.project.user_service.repository.UserRepository;
import com.project.user_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponseDto authenticate(LoginRequestDto dto){
        User user = userRepository.findByEmail(dto.getEmail()).
                orElseThrow(()-> new ResourceNotFoundException("Invalid email or Password"));
        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }
        String accessToken =jwtUtil.generateToken(user.getEmail(),user.getRole().toString());
        RefreshToken refreshToken =refreshTokenService.createRefreshToken(user.getEmail());
        return LoginResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken.getToken()).build();
    }
    public RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto dto){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(dto.getRefreshToken());
        User user = userRepository.findByEmail(refreshToken.getEmail()).orElseThrow(( )->new ResourceNotFoundException("User Not Found"));
        String newAccessToken=jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
        return RefreshTokenResponseDto.builder().accessToken(newAccessToken).build();
    }
    public void logout(String email) {

        refreshTokenService.deleteRefreshToken(email);
    }

}
