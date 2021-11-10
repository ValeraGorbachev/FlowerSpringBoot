package com.example.flowershopspringboot.controller;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
