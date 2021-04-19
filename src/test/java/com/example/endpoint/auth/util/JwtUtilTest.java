package com.example.endpoint.auth.util;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class JwtUtilTest {

    private JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void shouldExtractUsername() {
        UserDetails userDetails = User
                .builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();
        String jwt = jwtUtil.generateJWT(userDetails);

        String username = jwtUtil.extractUsername(jwt);

        assertEquals("admin", username);
    }

    @Test
    public void shouldExtractExpiration() {
        UserDetails userDetails = User
                .builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();
        String jwt = jwtUtil.generateJWT(userDetails);

        Date date = jwtUtil.extractExpiration(jwt);

        assertTrue(date != null);
    }

    @Test
    public void shouldExtractClaim() {
        UserDetails userDetails = User
                .builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();
        String jwt = jwtUtil.generateJWT(userDetails);

        String username = jwtUtil.extractClaim(jwt, Claims::getSubject);

        assertEquals("admin", username);
    }

    @Test
    public void shouldGenerateValidToken() {
        UserDetails userDetails = User
                .builder()
                .username("admin")
                .password("admin")
                .authorities(new ArrayList<>())
                .build();

        String jwt = jwtUtil.generateJWT(userDetails);

        assertTrue(jwtUtil.validateToken(jwt, userDetails));
    }
}
