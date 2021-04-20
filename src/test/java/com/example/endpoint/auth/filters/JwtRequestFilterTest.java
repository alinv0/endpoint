package com.example.endpoint.auth.filters;

import com.example.endpoint.auth.util.JwtUtil;
import com.example.endpoint.user.servkce.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtRequestFilterTest {
    @Mock
    private UserService userService;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private UserDetails userDetails;
    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    private String authorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxODc5NjYzNSwiaWF0IjoxNjE4Nzk1NzM1fQ.AserNfW7HJvvxQjJWQ7WRHP5XUwpxXdUB7yCQTmGDGo";
    private static MockedStatic<SecurityContextHolder> securityContextHolder;

    @BeforeAll
    public static void setup() {
        securityContextHolder = mockStatic(SecurityContextHolder.class);
    }

    @AfterAll
    public static void afterAll() {
        securityContextHolder.close();
    }

    @Test
    @SneakyThrows
    public void shouldDoFilterInternal() {
        String jwt = authorization.substring(7);
        securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        when(request.getHeader("Authorization")).thenReturn(authorization);
        when(jwtUtil.extractUsername(jwt)).thenReturn("admin");
        when(userService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtUtil.validateToken(jwt, userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);
        verify(request).getHeader("Authorization");
        verify(filterChain).doFilter(request, response);
        verify(securityContext).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    @SneakyThrows
    public void shouldNotSetAuthorizationInDoFilterInternalIfUsernameIsNull() {
        String jwt = authorization.substring(7);
        securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        when(request.getHeader("Authorization")).thenReturn(authorization);
        when(jwtUtil.extractUsername(jwt)).thenReturn(null);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);
        verify(request).getHeader("Authorization");
        verify(filterChain).doFilter(request, response);
        verify(securityContext, never()).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    @SneakyThrows
    public void shouldAuthorizeInDoFilterInternalIfAuthorizationIsNull() {
        authorization = null;
        securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        when(request.getHeader("Authorization")).thenReturn(authorization);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        verify(request).getHeader("Authorization");
        verify(userService, never()).loadUserByUsername(anyString());
        verify(securityContext, never()).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    @SneakyThrows
    public void shouldAuthorizeInDoFilterInternalIfAuthorizationNotStartsWithBearer() {
        authorization = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYxODc5NjYzNSwiaWF0IjoxNjE4Nzk1NzM1fQ.AserNfW7HJvvxQjJWQ7WRHP5XUwpxXdUB7yCQTmGDGo";
        securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        when(request.getHeader("Authorization")).thenReturn(authorization);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);
        verify(request).getHeader("Authorization");
        verify(userService, never()).loadUserByUsername(anyString());
        verify(securityContext, never()).setAuthentication(any(UsernamePasswordAuthenticationToken.class));
    }
}
