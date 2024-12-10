package com.example.demo.dto.auth;

public record LoginResponse(
    String token, Integer instructor, Long id) { }
