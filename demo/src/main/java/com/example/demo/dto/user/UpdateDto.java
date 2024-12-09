package com.example.demo.dto.user;

public record UpdateDto(
    String bio,
    String name,
    String email,
    String password,
    String gitUserName
) {}
