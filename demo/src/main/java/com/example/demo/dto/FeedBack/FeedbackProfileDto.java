package com.example.demo.dto.FeedBack;

import com.example.demo.dto.interaction.InteractionDataDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.user.GiverDto;

public record FeedbackProfileDto(
    Long id,
    String feedback,
    Integer stars,
    Boolean visibility,
    InteractionDataDto interaction,
    GiverDto giver,
    ProjectDataDto project
) {}