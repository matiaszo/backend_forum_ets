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
@Table(name = "tbInteraction")
public class InteractionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_interaction;
    
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;

    @Column
    private String type;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    public Long getId_interaction() {
        return id_interaction;
    }
    
    public void setId_interaction(Long id_interaction) {
        this.id_interaction = id_interaction;
    }
    
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
