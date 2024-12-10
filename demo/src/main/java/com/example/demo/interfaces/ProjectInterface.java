package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.model.ProjectModel;

public interface ProjectInterface
{
    public List<ProjectModel> getUserProjects(Long userId);
}