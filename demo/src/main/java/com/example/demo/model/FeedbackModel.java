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
@Table(name = "tbFeedback")
public class FeedbackModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Column
    private Boolean visibility; // public é palavra reservada. TRUE: público, FALSE: privado

    @Column
    private String feedback;

    @Column
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;


    @ManyToOne
    @JoinColumn(name = "id_receptor")
    private UserModel receptor;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectModel project;
    
    //! GETTERS AND SETTERS
    
    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }

    public UserModel getReceptor() {
        return receptor;
    }

    public void setReceptor(UserModel receptor) {
        this.receptor = receptor;
    }
    
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public InteractionModel getInteraction() {
        return interaction;
    }

    public void setInteraction(InteractionModel interaction) {
        this.interaction = interaction;
    }
}
