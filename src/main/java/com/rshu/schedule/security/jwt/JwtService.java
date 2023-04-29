package com.rshu.schedule.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.security.Key;
import java.util.*;
import java.util.function.Function;


@Service
public class JwtService {

    private static final String SECRET_SIGNING_KEY = "294A404E635266556A586E327235753778214125442A472D4B6150645367566B";

    public Optional<String> getJwtFromRequest(HttpServletRequest request) {
        if (new AntPathMatcher().match("/admin/panel/**", request.getRequestURI())) {
           return Optional.ofNullable(
                   Arrays.stream(request.getCookies())
                           .filter(cookie -> cookie.getName().equals("tok"))
                           .findFirst()
                           .get()
                           .getValue()
           );
        } else if (new AntPathMatcher().match("/api/**", request.getRequestURI())){
            return Optional.of(request.getHeader("Authorization").substring(7).trim());
        }
        return Optional.empty();
    }

    public String getLogin(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String login = getLogin(token);
        return (login.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }


    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_SIGNING_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);
    }


}
