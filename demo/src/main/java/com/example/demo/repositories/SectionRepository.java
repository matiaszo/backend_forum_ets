package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SectionModel;

public interface SectionRepository extends JpaRepository<SectionModel, Long>{
    
}
