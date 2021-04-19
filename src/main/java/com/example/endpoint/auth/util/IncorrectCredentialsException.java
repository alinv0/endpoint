package com.example.endpoint.auth.util;

public class IncorrectCredentialsException extends Exception{
    public IncorrectCredentialsException(String message, Exception e) {
        super(message, e);
    }
}
