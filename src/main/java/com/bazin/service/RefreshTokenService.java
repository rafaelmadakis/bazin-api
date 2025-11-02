package com.bazin.service;

import com.bazin.entity.RefreshToken;
import com.bazin.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

  @Value("${jwt.refreshExpirationMillis}")
  private long refreshExpirationMillis;

  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
    this.refreshTokenRepository = refreshTokenRepository;
  }

  public RefreshToken createRefreshToken(String username) {
    RefreshToken refreshToken = new RefreshToken();
    refreshToken.setToken(UUID.randomUUID().toString());
    refreshToken.setUsername(username);
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshExpirationMillis));
    RefreshToken savedToken = refreshTokenRepository.save(refreshToken);
    System.out.println("Generated and saved refresh token: " + savedToken.getToken());
    return savedToken;
  }

  public Optional<RefreshToken> findByToken(String token) {
       return refreshTokenRepository.findByToken(token);
  }

  public boolean isTokenExpired(RefreshToken refreshToken) {
    return refreshToken.getExpiryDate().isBefore(Instant.now());
  }
}