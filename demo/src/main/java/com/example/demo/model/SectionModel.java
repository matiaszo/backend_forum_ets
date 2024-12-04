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
    private Long id_section;
    
    @Column
    private String name;

    @OneToMany(mappedBy="section", orphanRemoval = true)
    private List<TopicModel> topics;  
    
    //! GETTERS AND SETTERS
    public List<TopicModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicModel> topics) {
        this.topics = topics;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_section() {
        return id_section;
    }

    public void setId_section(Long id_section) {
        this.id_section = id_section;
    }
}
