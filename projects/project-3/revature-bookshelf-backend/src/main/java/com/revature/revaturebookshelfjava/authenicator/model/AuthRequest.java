package com.revature.revaturebookshelfjava.authenicator.model;

import lombok.Data;

@Data
public class AuthRequest {
    // Incoming user login requests are wrapped into this
    private String username;
    private String password;

}
