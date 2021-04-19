package com.example.endpoint.auth.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String username;
    String password;
}
