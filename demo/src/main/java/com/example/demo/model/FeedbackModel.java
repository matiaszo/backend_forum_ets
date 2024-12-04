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
}
