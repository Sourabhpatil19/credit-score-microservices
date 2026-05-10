package com.project.user_service.auth.service;

import com.project.user_service.auth.entity.RefreshToken;
import com.project.user_service.auth.repository.RefreshTokenRepository;
import com.project.user_service.expection.InvalidTokenException;
import com.project.user_service.expection.TokenExpiredException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService  {

    private final RefreshTokenRepository repository;


//Create User
    public RefreshToken createRefreshToken(String email) {
        // check duplicate email
        repository.deleteByEmail(email);
       RefreshToken refreshToken= RefreshToken.builder().token(UUID.randomUUID().toString()).email(email).
                                    expiryDate(LocalDateTime.now().plusDays(7)).
                   build();
         return  repository.save(refreshToken);

    }

    public RefreshToken verifyRefreshToken(String token){
        RefreshToken refreshToken= repository.findByToken(token).orElseThrow(()->new InvalidTokenException("Invalid refresh token"));
        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw  new TokenExpiredException("Token is expired");
        }
        return refreshToken;
    }
    public void deleteRefreshToken(String email) {

        repository.deleteByEmail(email);
    }







}
