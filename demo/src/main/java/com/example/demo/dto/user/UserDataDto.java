package com.example.demo.dto.user;

public record UserDataDto(
    String edv,
    String password,
    String name,
    String email,
    Boolean instructor
) {}