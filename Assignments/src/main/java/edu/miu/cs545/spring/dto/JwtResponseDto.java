package edu.miu.cs545.spring.dto;

import java.io.Serializable;

public class JwtResponseDto implements Serializable {
    private final String token;
    public JwtResponseDto(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
