package com.example.demo.model;

import java.sql.Timestamp;

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

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectModel project;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

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
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}