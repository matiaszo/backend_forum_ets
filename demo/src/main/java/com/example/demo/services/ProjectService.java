package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.interfaces.ProjectInterface;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.model.ProjectModel;

public class ProjectService implements ProjectInterface
{
    @Autowired
    ProjectRepository ProjectRep;

    public List<ProjectModel> getUserProjects(Long userId)
    {
        return ProjectRep.findByUser(userId);
    }
}