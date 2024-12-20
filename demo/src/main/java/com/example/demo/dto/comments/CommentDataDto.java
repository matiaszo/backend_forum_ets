package com.example.demo.dto.comments;

import com.example.demo.dto.comment.MentionDto;
import com.example.demo.dto.user.UserCommentDto;

public record CommentDataDto(
    Long id,
    String content,
    Integer likes,
    UserCommentDto user,
    MentionDto mention
) {}
