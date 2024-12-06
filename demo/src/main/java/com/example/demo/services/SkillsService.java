package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.skills.SkillDataDto;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.model.SkillsModel;
import com.example.demo.repositories.SkillsRepository;

public class SkillsService implements SkillsInterface{

    @Autowired
    SkillsRepository repo;

    @Override
    public Boolean validateName(String name) {
        var results = repo.findByName(name.toUpperCase());

        if (results.size() > 0) {
            return false;
        }

        return true;
    }

    @Override
    public void Register(SkillDataDto data) {
        SkillsModel skl = new SkillsModel();

        skl.setName(data.name().toUpperCase());
        skl.setImage(data.image());

        repo.save(skl);
    }

    @Override
    public SkillsModel delete(Long id) {
        var found = repo.findById(id);

        if (found.isEmpty())
            return null;

        SkillsModel skill = found.get(); 

        repo.delete(skill);
        return skill;

    }

    @Override
    public SkillsModel update(Long id, SkillDataDto data) {
        var found = repo.findById(id);

        if (found.isEmpty())
            return null;

        SkillsModel skill = found.get(); 

        if (data.image() != null)
            skill.setImage(data.image());

        if (data.name()!= null)
            skill.setName(data.name()); 

        return skill;
    }
    
}
