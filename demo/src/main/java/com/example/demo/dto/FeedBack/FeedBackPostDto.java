package com.example.demo.dto.FeedBack;

public record FeedBackPostDto(
    Long idUser,
    Long idProject,
    String text,
    Integer stars
) {}
