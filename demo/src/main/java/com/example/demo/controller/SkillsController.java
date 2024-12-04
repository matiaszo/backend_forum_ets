package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.skills.*;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.model.SkillsModel;
import com.example.demo.repositories.SkillsRepository;

@RestController
@RequestMapping("/skills")
public class SkillsController {
    
    @Autowired
    SkillsInterface service;

    @Autowired
    SkillsRepository repo;

    @PostMapping
    public ResponseEntity<SkillDataDto> create(@RequestBody SkillDataDto skill) {
        
        if (!service.validateName(skill.name()) || skill.name() == null || skill.image() == null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        
        service.Register(skill.name().toUpperCase(), skill.image());
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SkillDataDto>> getAll() {
        
        var results = repo.findAll();

        if (results == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

        var allSkills = new ArrayList<SkillDataDto>();

        for (SkillsModel skillsModel : results) {
            SkillDataDto newSkill = new SkillDataDto(skillsModel.getName(), skillsModel.getImage());
            
            allSkills.add(newSkill);
        }

        return new ResponseEntity<>(allSkills, HttpStatus.ACCEPTED);
    }
}
