package com.example.demo.dto.FeedBack;

import com.example.demo.dto.interaction.InteractionDataDto;
import com.example.demo.dto.project.ProjectDataDto;
import com.example.demo.dto.user.UserDataDto;

public record feddbackProfileDto(
    Long id,
    String feedback,
    Integer stars,
    Boolean visibility,
    InteractionDataDto interaction,
    UserDataDto receptor,
    ProjectDataDto project
) {}
