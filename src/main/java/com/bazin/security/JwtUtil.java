package com.bazin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationMillis}")
  private long jwtExpirationMillis;

  @Value("${jwt.refreshExpirationMillis}")
  private long refreshExpirationMillis;

  private Key signingKey;

  @PostConstruct
  public void init() {
    signingKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
  }

  public String generateAccessToken(String username) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + jwtExpirationMillis);
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(exp)
        .signWith(signingKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateRefreshToken(String username) {
    Date now = new Date();
    Date exp = new Date(now.getTime() + refreshExpirationMillis);
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(now)
        .setExpiration(exp)
        .signWith(signingKey, SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return getClaims(token).getSubject();
  }

  public boolean isTokenValid(String token) {
    try {
      Claims claims = getClaims(token);
      return claims.getExpiration().after(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(signingKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }
}