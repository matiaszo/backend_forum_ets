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
@Table( name = "tbUser")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;
    
    @Column
    private String name;
    
    @Column
    private String edv;
    
    @Column
    private String password;
    
    @Column
    private Boolean instructor;
    
    @Column
    private String image;
    
    @Column
    private String bio;
    
    @Column
    private String email;
    
    @Column
    private String gitUsername;

    @Column
    private Integer interactionNumber;

    public Integer getInteractionNumber() {
        return interactionNumber;
    }

    public void setInteractionNumber(Integer interactionNumber) {
        this.interactionNumber = interactionNumber;
    }

    //! fOREGIN KEYS ENVIADAS
    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<InterestModel> interest;

    @OneToMany(mappedBy="receptor", orphanRemoval = true)
    private List<FeedbackModel> feedbacks;
    
    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<UserSkillModel> skills;  

    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<InteractionModel> interactions;  
    
    @OneToMany(mappedBy="user", orphanRemoval = true)
    private List<EventModel> events;  
    
    @OneToMany(mappedBy="creator", orphanRemoval = true)
    private List<SectionModel> sections;  
    
    //! GETTERS E SETTERS
    public String getGitUsername() {
        return gitUsername;
    }
    
    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public Boolean getInstructor() {
        return instructor;
    }
    
    public void setInstructor(Boolean instructor) {
        this.instructor = instructor;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEdv() {
        return edv;
    }
    
    public void setEdv(String edv) {
        this.edv = edv;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public List<InterestModel> getInterest() {
        return interest;
    }

    public void setInterest(List<InterestModel> interest) {
        this.interest = interest;
    }

    public List<FeedbackModel> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackModel> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<UserSkillModel> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkillModel> skills) {
        this.skills = skills;
    }

    public List<InteractionModel> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<InteractionModel> interactions) {
        this.interactions = interactions;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
