package com.example.endpoint.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AuthenticationResponse {

    @Getter
    private final String jwt;
}
