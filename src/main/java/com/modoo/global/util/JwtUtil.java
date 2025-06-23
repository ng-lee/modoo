package com.modoo.global.util;

import com.modoo.global.constant.TokenType;
import com.modoo.global.dto.jwt.AuthenticationDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    private final String secretKey;
    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;

    public JwtUtil(@Value("${jwt.secretKey}") String secretKey,
                   @Value("${jwt.access-token-expire-time}") String accessTokenExpirationTime,
                   @Value("${jwt.refresh-token-expire-time}") String refreshTokenExpirationTime
    ) {
        this.secretKey = secretKey;
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    /**
     * access, refresh token 생성
     * @param memberCd
     * @return 토큰 정보
     */
    public AuthenticationDto createToken(String memberCd) {
        return AuthenticationDto.builder()
                .memberCd(memberCd)
                .accessToken(this.createAccessToken(memberCd))
                .accessTokenExpirationTime(DateConverter.dateToLocalDateTime(this.getAccessTokenExpirationTime()))
                .refreshToken(this.createRefreshToken(memberCd))
                .refreshTokenExpirationTime(DateConverter.dateToLocalDateTime(this.getRefreshTokenExpirationTime()))
                .build();
    }

    /**
     * token 생성/검증에 사용될 서명 키 생성
     * @return
     */
    private Key createSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * access token 만료 시간 설정
     * @return
     */
    private Date getAccessTokenExpirationTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    /**
     * refresh token 만료 시간 설정
     * @return
     */
    private Date getRefreshTokenExpirationTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    /**
     * access token 생성
     * @param memberCd
     * @return
     */
    public String createAccessToken(String memberCd) {
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("type", TokenType.ACCESS)
                .claim("memberCd", memberCd)
                .setIssuedAt(new Date())
                .setExpiration(getAccessTokenExpirationTime())
                .signWith(createSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * refresh token 생성
     * @param memberCd
     * @return
     */
    public String createRefreshToken(String memberCd) {
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("type", TokenType.REFRESH)
                .claim("memberCd", memberCd)
                .setIssuedAt(new Date())
                .setExpiration(getRefreshTokenExpirationTime())
                .signWith(createSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * token 유효성 검증
     * @param token
     */
    public void validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(createSigningKey())
                .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.error("token is expired", e);
            throw new RuntimeException("token is expired");
        } catch (Exception e) {
            log.error("token is invalid", e);
            throw new RuntimeException("token is invalid");
        }
    }

    /**
     * token claim 추출
     * @param token
     * @return
     */
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(createSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("token parse failed", e);
            throw new RuntimeException("token parse failed");
        }
    }
}
