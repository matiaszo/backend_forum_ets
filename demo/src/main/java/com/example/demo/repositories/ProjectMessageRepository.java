package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProjectMessageModel;

public interface ProjectMessageRepository extends JpaRepository<ProjectMessageModel, Long>
{
    
}