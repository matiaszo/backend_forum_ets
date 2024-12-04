package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbLike")
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_like;
    
    @ManyToOne
    @JoinColumn(name = "id_interaction")
    private InteractionModel interaction;
    
    @ManyToOne
    @JoinColumn(name = "id_comment")
    private CommentModel comment;
    
    //! GETTERS AND SETTERS
    public CommentModel getComment() {
        return comment;
    }

    public void setComment(CommentModel comment) {
        this.comment = comment;
    }

    public Long getId_like() {
        return id_like;
    }
    
    public void setId_like(Long id_like) {
        this.id_like = id_like;
    }
    
    public InteractionModel getInteraction() {
        return interaction;
    }

    public void setInteraction(InteractionModel interaction) {
        this.interaction = interaction;
    }
}
