package com.example.demo.model;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbInteraction")
public class InteractionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_interaction;
    
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp date;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;
    
    // para colocar na tabela user
    // @OneToMany(mappedBy="user", orphanRemoval = true)
    // private List<InteractionModel> Users;  
    
    @OneToMany(mappedBy="chat", orphanRemoval = true)
    private List<MessageModel> Messages;  

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
}
