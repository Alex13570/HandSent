package ru.ivmiit.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.ivmiit.dto.request.AuthRequest;
import ru.ivmiit.dto.request.RefreshTokenRequest;
import ru.ivmiit.dto.request.SignUpRequest;
import ru.ivmiit.dto.response.AuthResponse;
import ru.ivmiit.services.AuthService;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
@CrossOrigin(//origins ="http://localhost:5173/", allowedHeaders = "origin",
        exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/sign-in", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse signIn(@RequestBody AuthRequest authRequestDto) {
        AuthResponse response = authService.authenticate(authRequestDto);
        System.out.println(response);
        return response;
    }

    @PostMapping(path ="/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        return authService.updateAccessTokenWithRefreshToken(tokenRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/sign-up", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse signUp(@RequestBody SignUpRequest request) {
        return authService.signUp(request);
    }

}