package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TopicModel;

public interface TopicRepository extends JpaRepository<TopicModel, Long>{

    public List<TopicModel> findById_Section(Long id);
    
}
