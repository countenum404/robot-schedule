package com.rshu.schedule.security.jwt;

import com.rshu.schedule.security.token.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final FilterServiceErrorHandler filterService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        final String jwt;
        final String userLogin;
        // check for an auth header and bearer token
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterService.handleMissedAuthToken(response);
            return;
        }
        // if prev is ok, then take a jwt token and check it
        jwt = authorizationHeader.substring(7);

        // token should be checked for expiration, corresponding message is sent if it is expired
        try {
            userLogin = jwtService.getLogin(jwt);
        } catch (ExpiredJwtException jwtException) {
            filterService.handleExpiredAuthToken(response);
            return;
        } catch (MalformedJwtException malformedJwtException){
            filterService.badTokenException(response);
            return;
        }

        if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userLogin);
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private static final List<String> shouldNotFilterUrls = Arrays.asList("/api/auth/**", "/h2-console/**");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return shouldNotFilterUrls.stream().anyMatch(i -> new AntPathMatcher().match(i, request.getRequestURI()));
    }
}
