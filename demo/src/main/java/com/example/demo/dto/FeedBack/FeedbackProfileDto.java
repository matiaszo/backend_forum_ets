package com.example.demo.dto.FeedBack;

import com.example.demo.dto.interaction.InteractionDataDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.user.GiverDto;

public record FeedbackProfileDto(
    Integer stars,
    String text,
    Boolean visibility,
    String projectName,
    GiverDto giver
) {}