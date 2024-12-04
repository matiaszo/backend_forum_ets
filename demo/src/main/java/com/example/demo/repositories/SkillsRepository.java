package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SkillsModel;

public interface SkillsRepository extends JpaRepository<SkillsModel, Long>{
    List<SkillsModel> findByName(String name); 
}
