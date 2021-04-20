package com.example.endpoint.auth.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationRequest {
    String username;
    String password;
}
