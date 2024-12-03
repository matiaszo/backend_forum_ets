package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbUser")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    private String name;
    
    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<UserSkillModel> Skills;  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String edv;

    public String getEdv() {
        return edv;
    }

    public void setEdv(String edv) {
        this.edv = edv;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Boolean instructor;

    public Boolean getInstructor() {
        return instructor;
    }

    public void setInstructor(Boolean instructor) {
        this.instructor = instructor;
    }

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private String email;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String gitUsername;

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }
    
}
