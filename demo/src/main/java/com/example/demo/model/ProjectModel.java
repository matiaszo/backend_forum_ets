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

    @Column
    private String image;

    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<FeedbackModel> feedbacks;
    
    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<ProjectMessageModel> projects;

    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<ProjectGoalModel> goals;

    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<UserModel> members;

    @OneToMany(mappedBy="project", orphanRemoval = true)
    private List<ProjectMessageModel> messages;


    //! GETTERS AND SETTERS
    public List<ProjectMessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<ProjectMessageModel> messages) {
        this.messages = messages;
    }

    public List<UserModel> getMembers() {
        return members;
    }

    public void setMembers(List<UserModel> members) {
        this.members = members;
    }

    public List<ProjectGoalModel> getGoals() {
        return goals;
    }

    public void setGoals(List<ProjectGoalModel> goals) {
        this.goals = goals;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}