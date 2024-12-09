package com.example.demo.model;

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
@Table(name = "tbMessage")
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_message;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_chat")
    private ChatModel chat;

    @OneToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;

    public Long getId_message() {
        return id_message;
    }

    public void setId_message(Long id_message) {
        this.id_message = id_message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ChatModel getChat() {
        return chat;
    }

    public void setChat(ChatModel chat) {
        this.chat = chat;
    }

    public InteractionModel getInteraction() {
        return interaction;
    }

    public void setInteraction(InteractionModel interaction) {
        this.interaction = interaction;
    }

}
