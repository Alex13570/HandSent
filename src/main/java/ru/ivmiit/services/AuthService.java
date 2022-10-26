package ru.ivmiit.services;

import ru.ivmiit.dto.request.AuthRequest;
import ru.ivmiit.dto.request.RefreshTokenRequest;
import ru.ivmiit.dto.request.SignUpRequest;
import ru.ivmiit.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequestDto);
    AuthResponse updateAccessTokenWithRefreshToken(RefreshTokenRequest tokenRequest);
    AuthResponse signUp(SignUpRequest request);
}
