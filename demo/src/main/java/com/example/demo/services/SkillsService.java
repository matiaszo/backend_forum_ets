package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

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
    public void Register(String name, String image) {
        SkillsModel skl = new SkillsModel();

        skl.setName(name);
        skl.setImage(image);

        repo.save(skl);
    }
    
}
