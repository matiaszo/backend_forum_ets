package com.example.demo.dto.user;

public record UpdateDto(
    String bio,
    String name,
    String email,
    String image,
    String password,
    String gitUsername
) {}
