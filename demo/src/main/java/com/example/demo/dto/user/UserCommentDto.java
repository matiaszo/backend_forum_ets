package com.example.demo.dto.user;

public record UserCommentDto (
    Long id,
    String name,
    Boolean instructor,
    String image
) {}
