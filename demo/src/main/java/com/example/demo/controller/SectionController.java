package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.interfaces.SectionInterface;

@RestController
@RequestMapping("/forum")
public class SectionController {
    
    @Autowired
    SectionInterface service;

    @PostMapping("/create")
    public ResponseEntity<Integer> create(@RequestAttribute("token") Token token, @RequestBody CreateSectionDTO data) {
        
        var res = service.createSection(data, token.userId());

        if (res != 10)
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
        
    }
}
