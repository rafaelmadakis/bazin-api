package com.bazin.controller;

import com.bazin.dto.RefreshTokenRequestDTO;
import com.bazin.entity.RefreshToken;
import com.bazin.security.JwtUtil;
import com.bazin.service.RefreshTokenService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;
  private final RefreshTokenService refreshTokenService;


  public AuthController(UserDetailsService userDetailsService, JwtUtil jwtUtil, RefreshTokenService refreshTokenService) {
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
    this.refreshTokenService = refreshTokenService;
  }

  public static class LoginRequest {

    public String username;
    public String password;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest req) {
    try {
      UserDetails user = userDetailsService.loadUserByUsername(req.username);
      boolean matches = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(req.password, user.getPassword());
      if (!matches) {
        throw new BadCredentialsException("Credenciais inválidas");
      }

      String token = jwtUtil.generateAccessToken(user.getUsername());
      RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
      return ResponseEntity.ok(Map.of(
          "token", token,
          "refreshToken", refreshToken.getToken()));
    } catch (Exception ex) {
      return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
    }
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<String> refreshAccessToken(@RequestBody RefreshTokenRequestDTO request) {
    System.out.println("Received refresh token: " + request.getRefreshToken());
    RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken())
        .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));

    System.out.println("Found refresh token for user: " + refreshToken.getUsername());

    if (refreshTokenService.isTokenExpired(refreshToken)) {
      throw new IllegalArgumentException("Refresh token expired");
    }

    String newAccessToken = jwtUtil.generateRefreshToken(refreshToken.getUsername());
    return ResponseEntity.ok(newAccessToken);
  }
}

