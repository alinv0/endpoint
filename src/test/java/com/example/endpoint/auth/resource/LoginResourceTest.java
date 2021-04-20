package com.example.endpoint.auth.resource;

import com.example.endpoint.auth.model.AuthenticationRequest;
import com.example.endpoint.auth.model.AuthenticationResponse;
import com.example.endpoint.auth.util.IncorrectCredentialsException;
import com.example.endpoint.auth.util.JwtUtil;
import com.example.endpoint.user.servkce.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginResourceTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private UserDetails userDetails;
    @InjectMocks
    private LoginResource loginResource;

    @Captor
    private ArgumentCaptor<UsernamePasswordAuthenticationToken> tokenArgumentCaptor;

    private static MockedStatic<ResponseEntity> response;

    @BeforeAll
    public static void setup() {
        response = mockStatic(ResponseEntity.class);
    }

    @AfterAll
    public static void afterAll() {
        response.close();
    }

    @Test
    @SneakyThrows
    public void shouldLogin() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest
                .builder().username("admin").password("admin").build();
        String jwt = "JWTSample";
        when(userService.loadUserByUsername(authenticationRequest.getUsername())).thenReturn(userDetails);
        when(jwtUtil.generateJWT(userDetails)).thenReturn(jwt);

        loginResource.login(authenticationRequest);

        verify(authenticationManager).authenticate(tokenArgumentCaptor.capture());
        UsernamePasswordAuthenticationToken tokenValue = tokenArgumentCaptor.getValue();
        assertEquals("admin", tokenValue.getPrincipal());
        assertEquals("admin", tokenValue.getCredentials());
        verify(userService).loadUserByUsername(authenticationRequest.getUsername());
        verify(jwtUtil).generateJWT(userDetails);
        response.verify(() -> ResponseEntity.ok(any(AuthenticationResponse.class)));
    }


    @Test
    @SneakyThrows
    public void shouldNotLoginAndThrowException() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest
                .builder().username("admin").password("admin").build();
        String message = "Incorrect username or password";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException(message));

        Exception exception = assertThrows(IncorrectCredentialsException.class,
                () -> loginResource.login(authenticationRequest));
        assertEquals(message, exception.getMessage());
    }
}
