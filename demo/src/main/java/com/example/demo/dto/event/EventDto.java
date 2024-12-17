package com.example.demo.dto.event;

import com.example.demo.dto.user.UserSimplifiedDto;

public record EventDto
(
    Long id,
    String type,
    String text,
    String image,
    String title,
    UserSimplifiedDto user
){}