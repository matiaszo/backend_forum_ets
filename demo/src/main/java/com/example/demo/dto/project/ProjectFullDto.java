package com.example.demo.dto.project;

import java.util.List;

import com.example.demo.dto.user.UserCommentDto;

public record ProjectFullDto
(
    Long id,
    String name,
    String Description,
    List<String> goals,

    List<UserCommentDto> users,
    List<ProjectMessageDto> messages
){}