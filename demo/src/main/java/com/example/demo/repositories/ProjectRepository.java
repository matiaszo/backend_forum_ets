package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProjectModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Long>{
    
}
