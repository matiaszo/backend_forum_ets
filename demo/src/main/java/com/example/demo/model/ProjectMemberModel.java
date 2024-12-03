package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
@Table(name = "tbProjectMember")
public class ProjectMemberModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long projectMemberId;

    public Long getProjectMemberId() {
        return projectMemberId;
    }

    public void setProjectMemberId(Long projectMemberId) {
        this.projectMemberId = projectMemberId;
    }

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectModel skill;
}




