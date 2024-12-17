package com.example.demo.dto.FeedBack;

import com.example.demo.dto.user.GiverDto;

public record FeedbackProfileDto(
    Long id,
    Integer stars,
    String text,
    Boolean visibility,
    String projectName,
    GiverDto giver
) {}