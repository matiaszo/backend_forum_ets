package com.example.demo.interfaces;

import com.example.demo.dto.skills.SkillDataDto;

public interface UserSkillInterface {
    public SkillDataDto Register(Long user, Long skill);
}
