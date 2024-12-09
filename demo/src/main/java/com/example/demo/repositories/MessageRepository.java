package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    
}
