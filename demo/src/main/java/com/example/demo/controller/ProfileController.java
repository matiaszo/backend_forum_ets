package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.skills.*;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.interfaces.UserSkillInterface;
import com.example.demo.model.SkillsModel;
import com.example.demo.repositories.SkillsRepository;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    SkillsInterface service;

    @Autowired
    UserSkillInterface userSkillService;

    @Autowired
    UserInterface userService;

    @Autowired
    SkillsRepository repo;

    @PostMapping("/skill/{id}")
    public UserSkillDto createSkill(@PathVariable Long id, @RequestBody Long skill) {
        userSkillService.Register(id, skill);
        
        return new UserSkillDto(id, skill);
    }

    @DeleteMapping("/skill/{id}")
    public UserSkillDto deleteSkill(@PathVariable Long id, @RequestBody Long skill) {
        

        userSkillService.Register(id, skill);
        
        return new UserSkillDto(id, skill);
    }

    // @GetMapping
    // public ResponseEntity<List<SkillDataDto>> getAll() {
        
    //     var results = repo.findAll();

    //     if (results == null) {
    //         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    //     }

    //     var allSkills = new ArrayList<SkillDataDto>();

    //     for (SkillsModel skillsModel : results) {
    //         SkillDataDto newSkill = new SkillDataDto(skillsModel.getName(), skillsModel.getImage());
            
    //         allSkills.add(newSkill);
    //     }

    //     return new ResponseEntity<>(allSkills, HttpStatus.ACCEPTED);
    // }

    // @GetMapping("/{id}")
    // public ResponseEntity<List<SkillDataDto>> getById(@PathVariable Long id) {
        
    //     var results = repo.queryByUser(id);

    //     if (results == null) {
    //         return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    //     }

    //     return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
    // }
}
