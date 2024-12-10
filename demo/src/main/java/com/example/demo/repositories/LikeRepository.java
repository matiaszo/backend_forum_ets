package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LikeModel;

public interface LikeRepository extends JpaRepository<LikeModel, Long>{
    
}
