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
@Table(name = "tbSection")
public class SectionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    
    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String image;

    
    
    @OneToMany(mappedBy="section", orphanRemoval = true)
    private List<TopicModel> topics;  
    
    //! GETTERS AND SETTERS

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<TopicModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicModel> topics) {
        this.topics = topics;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long id_section) {
        this.sectionId = id_section;
    }
}
