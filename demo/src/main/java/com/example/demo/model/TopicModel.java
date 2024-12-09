package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbTopic")
public class TopicModel {
    @Id
    @GeneratedValue
    private Long id_topic;
    
    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_section")
    private SectionModel section;
    
    public SectionModel getSection() {
        return section;
    }

    public void setSection(SectionModel section) {
        this.section = section;
    }

    @ManyToOne
    @JoinColumn(name = "id_main_comment")
    private CommentModel comment;

    public CommentModel getComment() {
        return comment;
    }

    public void setComment(CommentModel comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId_topic() {
        return id_topic;
    }

    public void setId_topic(Long id_topic) {
        this.id_topic = id_topic;
    }
}
