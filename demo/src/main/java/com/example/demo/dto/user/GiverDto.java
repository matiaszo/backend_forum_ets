package com.example.demo.dto.user;

public record GiverDto(
    Long id,
    String edv,
    String name,
    Boolean instructor,
    String image
) {}
