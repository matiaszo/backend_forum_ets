package com.example.demo.model;

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
@Table(name = "tbTopic")
public class TopicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;
    
    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_section")
    private SectionModel section;

    @ManyToOne
    @JoinColumn(name = "id_main_comment")
    private CommentModel comment;

    @OneToMany(mappedBy = "topic", orphanRemoval = true)
    private List<CommentModel> allComments;


    public List<CommentModel> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<CommentModel> allComments) {
        this.allComments = allComments;
    }

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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long id_topic) {
        this.topicId = id_topic;
    }
    
    public SectionModel getSection() {
        return section;
    }

    public void setSection(SectionModel section) {
        this.section = section;
    }

}
