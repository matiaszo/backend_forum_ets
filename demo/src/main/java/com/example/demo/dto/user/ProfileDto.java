package com.example.demo.dto.user;

import java.util.List;

import com.example.demo.dto.interest.InterestProfileDto;
import com.example.demo.dto.skills.SkillProfileDto;

public record ProfileDto(
    Long id, 
    String bio,
    String edv,
    String email,
    String gitUserName,
    String image,
    String name,
    Boolean instructor,
    List<SkillProfileDto> skills,
    List<InterestProfileDto> interests
) {}
