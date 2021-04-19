package com.example.endpoint.auth.user;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    @SneakyThrows
    public void shouldLoadUserByUsername() {
        String username = "admin";
        String password = "admin";
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        when(userRepository.findByUsername(username)).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
    }

    @Test
    @SneakyThrows
    public void shouldNotLoadUserByUsernameAndThrowException() {
        String message = "User not found.";

        Exception exception = assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("abc"));
        assertEquals(message, exception.getMessage());
    }
}