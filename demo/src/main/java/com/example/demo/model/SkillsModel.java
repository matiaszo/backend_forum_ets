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
@Table(name= "tbSkills")
public class SkillsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_skills;
    
    @Column
    private String name;
    
    @Column
    private String image;

    @OneToMany(mappedBy="skill", orphanRemoval = true)
    private List<UserSkillModel> Users;  
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_skills() {
        return id_skills;
    }

    public void setId_skills(Long id_skills) {
        this.id_skills = id_skills;
    }

    public List<UserSkillModel> getUsers() {
        return Users;
    }

    public void setUsers(List<UserSkillModel> users) {
        Users = users;
    }
}
