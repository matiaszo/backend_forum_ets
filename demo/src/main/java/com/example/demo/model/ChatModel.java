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
@Table(name = "tbChat")
public class ChatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_chat;

    @Column
    private String name;

    @OneToMany(mappedBy="interaction", orphanRemoval = true)
    private List<MessageModel> Messages;  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_chat() {
        return id_chat;
    }

    public void setId_chat(Long id_chat) {
        this.id_chat = id_chat;
    }
}
