package com.example.todo_app.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
