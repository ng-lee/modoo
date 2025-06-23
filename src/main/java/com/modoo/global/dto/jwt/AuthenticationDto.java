package com.modoo.global.dto.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class AuthenticationDto {
    private String memberCd;
    private String accessToken;
    private LocalDateTime accessTokenExpirationTime;
    private String refreshToken;
    private LocalDateTime refreshTokenExpirationTime;
}
