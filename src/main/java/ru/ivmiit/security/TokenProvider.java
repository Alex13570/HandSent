package ru.ivmiit.security;

import org.springframework.security.core.Authentication;
import ru.ivmiit.models.RefreshToken;
import ru.ivmiit.models.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String generateAccessToken(String email, String role);
    RefreshToken generateRefreshToken(UserEntity userAccount);
    boolean isValidAccessToken(String token);
    Authentication getAuthenticationFromAccessToken(String token);
    String getUsernameFromAccessToken(String token);
    String getAccessTokenFromHeader(HttpServletRequest request);
}
