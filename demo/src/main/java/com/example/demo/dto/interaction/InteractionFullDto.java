package com.example.demo.dto.interaction;

import java.util.Date;

public record InteractionFullDto
(
    String type,
    Date timestamp,
    InteractionExtra content
){}