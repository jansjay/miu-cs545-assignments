package edu.miu.cs545.spring.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class JwtResponseDto implements Serializable {
    private final String token;
    private final Date expiresAt;
    private final String refreshToken;
    private final Date refreshTokenExpiresAt;
    public JwtResponseDto(String token, Date expiresAt, String refreshToken, Date refreshTokenExpiresAt) {

        this.token = token;
        this.expiresAt = expiresAt;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }
}
