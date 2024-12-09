package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CommentModel;

public interface CommentRepository extends JpaRepository<CommentModel, Long>{
    
}
