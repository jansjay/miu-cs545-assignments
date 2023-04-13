package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.JwtResponseDto;
import edu.miu.cs545.spring.dto.RefreshDto;

public interface AuthService {
    JwtResponseDto getJwtTokens(String username);
    JwtResponseDto refresh(RefreshDto refreshDto);
    void logout(String name);
}
