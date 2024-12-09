package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.CreateTopicFullInfoDTO;
import com.example.demo.dto.topics.TopicCreationResponseDTO;
import com.example.demo.interfaces.TopicInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicInterface service;
    
    @PostMapping
    public ResponseEntity<TopicCreationResponseDTO> create(@RequestAttribute("token") Token token, @RequestBody CreateTopicDTO info) {

        if (!token.instructor())
            return new ResponseEntity<>(new TopicCreationResponseDTO(null, "youre not adm lero lero :p"), HttpStatus.FORBIDDEN);
    
        if (!service.verifyTopicTitle(info.title()))
            return new ResponseEntity<>(new TopicCreationResponseDTO(null, "1"), HttpStatus.UNPROCESSABLE_ENTITY);

        if (!service.verifyTopicInputs(info))
            return new ResponseEntity<>(new TopicCreationResponseDTO(null, "2"), HttpStatus.BAD_REQUEST);

        

        var created = service.create(new CreateTopicFullInfoDTO(info, token.userId()));

        return new ResponseEntity<TopicCreationResponseDTO>(new TopicCreationResponseDTO(created, "10"), HttpStatus.CREATED);        
    }
    
}
