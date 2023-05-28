package com.rshu.schedule.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class FilterServiceErrorHandler {
    private static final String JSON_CONTENT_TYPE = "application/json";
    private final ObjectMapper objectMapper;

    private void setResponseContent(HttpServletResponse response){
        response.setContentType(JSON_CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void handleMissedAuthToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (new AntPathMatcher().match("/api/**", request.getRequestURI())) {
            setResponseContent(response);
            response.getWriter().write(objectMapper.writeValueAsString("Missed Auth token"));
        } else {
            response.sendRedirect("/admin/login");
        }
    }

    public void handleExpiredAuthToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (new AntPathMatcher().match("/api/**", request.getRequestURI())) {
            setResponseContent(response);
            response.getWriter().write(objectMapper.writeValueAsString("Auth token is expired"));
        } else {
            response.sendRedirect("/admin/login");
        }

    }

    public void badTokenException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (new AntPathMatcher().match("/api/**", request.getRequestURI())) {
            setResponseContent(response);
            response.getWriter().write(objectMapper.writeValueAsString("Bad auth token"));
        } else {
            response.sendRedirect("/admin/login");
        }
    }

    public void handleUsernameNotFoundException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (new AntPathMatcher().match("/api/**", request.getRequestURI())) {
            setResponseContent(response);
            response.getWriter().write(objectMapper.writeValueAsString("Credentials is not found"));
        } else {
            response.sendRedirect("/admin/login");
        }
    }
}
