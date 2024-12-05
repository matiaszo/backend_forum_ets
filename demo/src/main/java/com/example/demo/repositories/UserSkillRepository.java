package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserSkillModel;

public interface UserSkillRepository extends JpaRepository<UserSkillModel, Long> {
    
}
