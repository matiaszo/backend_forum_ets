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
@Table(name = "tbSection")
public class SectionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String image;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel creator;

    
    
    @OneToMany(mappedBy="section", orphanRemoval = true)
    private List<TopicModel> topics;  
    
    //! GETTERS AND SETTERS
    
    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id_section) {
        this.id = id_section;
    }
}
