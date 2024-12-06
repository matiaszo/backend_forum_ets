package com.example.demo.dto.interaction;

import java.sql.Timestamp;

public record InteractionDataDto(
    Long id,
    String type,
    Timestamp date
) {}
