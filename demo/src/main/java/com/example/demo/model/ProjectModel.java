package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbProject")
public class ProjectModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_project;

    @Column
    private String name;

    @Column
    private String description;
    
    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<FeedbackModel> feedbacks;
    
    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<ProjectMessageModel> projects;

    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<ProjectGoalModel> goals;


    //! GETTERS AND SETTERS
    public Long getId_project() {
        return id_project;
    }

    public void setId_project(Long id_project) {
        this.id_project = id_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FeedbackModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<ProjectMessageModel> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectMessageModel> projects) {
        this.projects = projects;
    }

}