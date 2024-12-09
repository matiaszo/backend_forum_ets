package com.example.demo.dto.comments;

import com.example.demo.dto.user.UserCommentDto;
import com.example.demo.dto.comment.MentionDto;;

public record CommentDataDto(
    Long id,
    String content,
    UserCommentDto user,
    MentionDto mention
) {}
