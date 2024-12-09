package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbComment")
public class CommentModel {
    @Id
    @GeneratedValue
    private Long id_comment;

    @Column
    private String content;
    
    @OneToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;
    
    @ManyToOne
    @JoinColumn(name = "id_topic")
    private TopicModel topic;

    @OneToOne(orphanRemoval = false)
    @JoinColumn(name = "id_mention")
    private CommentModel mention;
    
    //! GETTERS AND SETTERS
    public CommentModel getMention() {
        return mention;
    }

    public void setMention(CommentModel mention) {
        this.mention = mention;
    }

    public TopicModel getTopic() {
        return topic;
    }

    public void setTopic(TopicModel topic) {
        this.topic = topic;
    }

    public InteractionModel getInteraction() {
        return interaction;
    }

    public void setInteraction(InteractionModel interaction) {
        this.interaction = interaction;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public Long getId_comment() {
        return id_comment;
    }

    public void setId_comment(Long id_comment) {
        this.id_comment = id_comment;
    }
}
