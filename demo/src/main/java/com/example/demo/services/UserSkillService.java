package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.skills.SkillDataDto;
import com.example.demo.interfaces.UserSkillInterface;
import com.example.demo.model.SkillsModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.UserSkillModel;
import com.example.demo.repositories.SkillsRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserSkillRepository;

public class UserSkillService implements UserSkillInterface {

    @Autowired
    UserRepository userRepo;

    @Autowired
    SkillsRepository skillRepo;

    @Autowired
    UserSkillRepository userSkillRepo;

    @Override
    public SkillDataDto Register(Long user, Long skill) {
        UserSkillModel model = new UserSkillModel();

        Optional<UserModel> userModel = userRepo.findById(user); 
        model.setUser(userModel.get());
        
        Optional<SkillsModel> skillModel = skillRepo.findById(skill);
        model.setSkill(skillModel.get());

        userSkillRepo.save(model);

        return new SkillDataDto(skillModel.get().getId_skills(), skillModel.get().getName(), skillModel.get().getImage());
    }
    
}
