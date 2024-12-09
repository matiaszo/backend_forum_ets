package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbProjectMessage")
public class ProjectMessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_project_message;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectModel project;

    @OneToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;

    //! GETTERS AND SETTERS
    public Long getId_project_message() {
        return id_project_message;
    }

    public void setId_project_message(Long id_project_message) {
        this.id_project_message = id_project_message;
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public InteractionModel getInteraction() {
        return interaction;
    }

    public void setInteraction(InteractionModel interaction) {
        this.interaction = interaction;
    }
}