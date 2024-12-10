package com.example.demo.dto.chat;

import com.example.demo.dto.user.UserCommentDto;

public record MessageDataDto(
    Long id,
    String text,
    UserCommentDto user
) {}
