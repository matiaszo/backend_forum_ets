package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProjectGoalModel;

public interface ProjectGoalRepository extends JpaRepository<ProjectGoalModel, Long>
{
    
}