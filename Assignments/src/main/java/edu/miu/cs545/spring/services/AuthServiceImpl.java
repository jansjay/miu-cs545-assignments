package edu.miu.cs545.spring.services;

import edu.miu.cs545.spring.dto.JwtResponseDto;
import edu.miu.cs545.spring.dto.RefreshDto;
import edu.miu.cs545.spring.models.AccessToken;
import edu.miu.cs545.spring.models.RefreshToken;
import edu.miu.cs545.spring.models.User;
import edu.miu.cs545.spring.repositories.RefreshTokenRepository;
import edu.miu.cs545.spring.repositories.UserRepository;
import edu.miu.cs545.spring.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthServiceImpl implements AuthService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    JwtUtil jwtUtil;
    @Override
    public JwtResponseDto getJwtTokens(String username) {
        final User userDetails = userRepository.findByName(username).orElseThrow();
        final String jwtToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(username);

        RefreshToken refreshTokenDb = new RefreshToken();
        refreshTokenDb.setToken(refreshToken);
        refreshTokenDb.setUser(userDetails);
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(jwtToken);
        accessToken.setRefreshToken(refreshTokenDb);
        accessToken.setUser(userDetails);
        List<AccessToken> accessTokens = new ArrayList<>();
        accessTokens.add(accessToken);
        refreshTokenDb.setAccessTokens(accessTokens);
        refreshTokenRepository.save(refreshTokenDb);


        return new JwtResponseDto(jwtToken,
                jwtUtil.getExpirationDate(jwtToken),
                refreshToken,
                jwtUtil.getExpirationDate(refreshToken));
    }

    @Override
    public JwtResponseDto refresh(RefreshDto refreshDto) {
        if(jwtUtil.validateToken(refreshDto.getRefreshToken())){
            final User userDetails = userRepository.findByName(jwtUtil.getUsernameFromToken(refreshDto.getRefreshToken())).orElseThrow();
            final String jwtToken = jwtUtil.generateToken(userDetails);
            final RefreshToken refreshToken = refreshTokenRepository.findById(refreshDto.getRefreshToken()).orElseThrow();
            AccessToken accessToken = new AccessToken();
            accessToken.setToken(jwtToken);
            accessToken.setRefreshToken(refreshToken);
            accessToken.setUser(userDetails);
            refreshToken.getAccessTokens().add(accessToken);
            refreshTokenRepository.save(refreshToken);

            return new JwtResponseDto(jwtToken,
                    jwtUtil.getExpirationDate(jwtToken),
                    refreshDto.getRefreshToken(),
                    jwtUtil.getExpirationDate(refreshDto.getRefreshToken()));
        }

        throw new JwtException("RefreshToken is not valid.");
    }

    @Override
    public void logout(String name) {
        refreshTokenRepository.deleteAll( refreshTokenRepository.findRefreshTokenByUserNameEquals(name));
    }
}
