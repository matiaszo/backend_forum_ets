package com.example.demo.dto.comments;

public record CreateCommentDTO(Long userId, Long mentionId, String content, Long topicId) {}
