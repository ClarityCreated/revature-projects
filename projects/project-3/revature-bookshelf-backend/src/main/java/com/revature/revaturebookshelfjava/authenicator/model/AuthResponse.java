package com.revature.revaturebookshelfjava.authenicator.model;

public class AuthResponse {
    // Response based on user login
    private String message;
    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
