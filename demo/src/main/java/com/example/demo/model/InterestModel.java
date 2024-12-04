package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "tbInterest")
public class InterestModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_interest;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;
    
    public Long getId_interest() {
        return id_interest;
    }

    public void setId_interest(Long id_interest) {
        this.id_interest = id_interest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
