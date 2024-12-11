package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionCreationResponseDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.dto.section.SectionTopicsDTO;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<SectionDTO> delete(@RequestAttribute Token token, @PathVariable Long id) {
        
        if (!token.instructor())
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        var res = service.delete(id);

        if (res == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<SectionDTO> patchSkill(@RequestAttribute("token") Token token, @PathVariable Long id, @RequestBody CreateSectionDTO data) {
        if (!token.instructor())
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        var res = service.update(id, data);

        return new ResponseEntity<>(res, HttpStatus.OK);
        

    }

    @GetMapping
    public ResponseEntity<ArrayList<SectionDTO>> getAllSections(String title, Integer page) {
        System.out.println("Searching for title: " + title);
        if(title == null)
        {
            title = "";
        }
        if(page == null)
        {
            page = 0;
        }

        var res = service.getSection(title, page, 10);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionTopicsDTO> getSingleSectionContoller(@PathVariable Long id) {
        
        var found = service.getSingleSection(id);
        System.out.println(found);

        return new ResponseEntity<>(found, HttpStatus.OK);
    }
    


}
