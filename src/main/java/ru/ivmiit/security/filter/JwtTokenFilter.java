package ru.ivmiit.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.ivmiit.exceptions.HandSentException;
import ru.ivmiit.security.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = tokenProvider.getAccessTokenFromHeader(request);
            if (tokenProvider.isValidAccessToken(token)) {
                SecurityContextHolder.getContext()
                        .setAuthentication(tokenProvider.getAuthenticationFromAccessToken(token));
            }
        } catch (HandSentException e) {
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
