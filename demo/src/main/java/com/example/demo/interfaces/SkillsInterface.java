package com.example.demo.interfaces;

import com.example.demo.dto.skills.SkillDataDto;
import com.example.demo.model.SkillsModel;

public interface SkillsInterface {
    public Boolean validateName(String name);
    public void Register(SkillDataDto data);
    public SkillsModel delete(Long id);
    public SkillsModel update(Long id, SkillDataDto data);
}
