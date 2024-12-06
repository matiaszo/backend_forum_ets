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
import com.example.demo.dto.section.SectionCreationResponseDTO;
import com.example.demo.interfaces.SectionInterface;

@RestController
@RequestMapping("/section")
public class SectionController {
    
    @Autowired
    SectionInterface service;

    @PostMapping("/create")
    public ResponseEntity<SectionCreationResponseDTO> create(@RequestAttribute("token") Token token, @RequestBody CreateSectionDTO data) {
        
        if (!token.instructor())
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        var res = service.verify(data);
        if (res != 10) {
            SectionCreationResponseDTO finalRes = new SectionCreationResponseDTO(null, res.toString());
            return new ResponseEntity<>(finalRes, HttpStatus.BAD_REQUEST);
        }

        var created = service.createSection(data);
        SectionCreationResponseDTO finalRes = new SectionCreationResponseDTO(created, "Section created succesfully!");
        return new ResponseEntity<>(finalRes, HttpStatus.CREATED);
        
    }
}
