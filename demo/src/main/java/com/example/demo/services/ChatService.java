package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.interfaces.ChatInterface;
import com.example.demo.repositories.ChatRepository;

public class ChatService implements ChatInterface {

    @Autowired
    ChatRepository repo;

    @Override
    public Boolean validateName(String name) {

        if (repo.findByName(name).size() > 0)
            return false;

        return true;
    }
    
}
