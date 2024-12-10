package com.example.demo.dto.chat;

import java.util.List;

public record ChatMessagesDto(
    Long id,
    String name,
    List<MessageDataDto> messages
) {}
