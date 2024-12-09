package com.example.demo.dto.comments;

public record CommentDTO(Long userId, Long topicId, String content, Long mentionId) {}
