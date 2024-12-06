package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tbUserSkills")
public class UserSkillModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user_skills;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "id_skill")
    private SkillsModel skill;

    public Long getId_user_skills() {
        return id_user_skills;
    }

    public void setId_user_skills(Long id_user_skills) {
        this.id_user_skills = id_user_skills;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public SkillsModel getSkill() {
        return skill;
    }

    public void setSkill(SkillsModel skill) {
        this.skill = skill;
    }
}
