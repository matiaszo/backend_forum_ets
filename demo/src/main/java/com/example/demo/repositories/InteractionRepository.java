package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.InteractionModel;

public interface InteractionRepository extends JpaRepository<InteractionModel, Long> {
    
}
