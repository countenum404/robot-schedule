package com.rshu.schedule.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void handleMissedAuthToken(HttpServletResponse response) throws IOException {
        setResponseContent(response);
        response.getWriter().write(objectMapper.writeValueAsString("Missed Auth token"));
    }

    public void handleExpiredAuthToken(HttpServletResponse response) throws IOException {
        setResponseContent(response);
        response.getWriter().write(objectMapper.writeValueAsString("Auth token is expired"));
    }

    public void badTokenException(HttpServletResponse response) throws IOException {
        setResponseContent(response);
        response.getWriter().write(objectMapper.writeValueAsString("Bad auth token"));
    }

    public void handleUsernameNotFoundException(HttpServletResponse response) throws IOException {
        setResponseContent(response);
        response.getWriter().write(objectMapper.writeValueAsString("Credentials is not found"));
    }
}
