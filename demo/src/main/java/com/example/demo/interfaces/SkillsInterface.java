package com.example.demo.interfaces;

import com.example.demo.model.SkillsModel;

public interface SkillsInterface {
    public Boolean validateName(String name);
    public void Register(String name, String image);
    public SkillsModel delete(Long id);
}
