package com.example.demo.dto.project;

import java.util.List;

public record NewProjectDto
(
    String name,
    String description,
    List<String> goals,
    List<Long> users
){}