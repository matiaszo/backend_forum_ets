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

    // @OneToMany(mappedBy="project", orphanRemoval = true)
    // private List<ProjectMessageModel> Projects;
    
    @ManyToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;

    // @OneToMany(mappedBy="interaction", orphanRemoval = true)
    // private List<ProjectMessageModel> Projects;
}
