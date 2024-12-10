package com.example.demo.dto.chat;

import java.util.Date;

import com.example.demo.dto.user.UserCommentDto;

public record MessageDataDto(
    Long id,
    Date date,
    String text,
    UserCommentDto user
) {}
