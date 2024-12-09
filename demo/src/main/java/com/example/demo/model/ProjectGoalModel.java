package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbProjectGoal")
public class ProjectGoalModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_project_goal;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectModel project;

    @Column
    private String description;

    //! GETTERS E SETTERS
    public Long getId_project_goal() {
        return id_project_goal;
    }

    public void setId_project_goal(Long id_project_goal) {
        this.id_project_goal = id_project_goal;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}