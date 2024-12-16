package com.example.demo.dto.user;

public record GiverDto(
    Long id,
    String name,
    Boolean instructor,
    String image
) {}
