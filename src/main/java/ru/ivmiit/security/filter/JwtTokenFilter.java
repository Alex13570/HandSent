package ru.ivmiit.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.ivmiit.dto.response.ErrorResponse;
import ru.ivmiit.exceptions.HandSentException;
import ru.ivmiit.exceptions.token.InvalidTokenException;
import ru.ivmiit.security.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private static final String[] PERMIT_ALL = {
            "/api/auth",
            "/swagger-ui",
            "/v3/api-docs"
    };

    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        for (String perm : PERMIT_ALL) {
            if (request.getRequestURI().startsWith(perm)){
                filterChain.doFilter(request, response);
                return;
            }
        }
        try {
            String token = tokenProvider.getAccessTokenFromHeader(request);
            if (tokenProvider.isValidAccessToken(token)) {
                SecurityContextHolder.getContext()
                        .setAuthentication(tokenProvider.getAuthenticationFromAccessToken(token));
                filterChain.doFilter(request, response);
            }else {
                throw new InvalidTokenException();
            }
        } catch (HandSentException e) {
            SecurityContextHolder.clearContext();
            ErrorResponse err = ErrorResponse.builder()
                    .status(601)
                    .message(e.getMessage())
                    .exceptionName(e.getClass().getSimpleName())
                    .timeStamp(Instant.now())
                    .path(request.getRequestURI())
                    .build();
            response.setStatus(601);
            response.addHeader("Content-Type", "application/json");
            response.getWriter().write(mapper.writeValueAsString(err));
            SecurityContextHolder.clearContext();
        }
    }
}
