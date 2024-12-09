package com.example.demo.dto.topics;

import java.util.List;

import com.example.demo.dto.comment.CommentDataDto;

public record TopicDataDto(
    Long id,
    String title,
    Long Section,
    CommentDataDto mainComment,
    List<CommentDataDto> comments
) {}