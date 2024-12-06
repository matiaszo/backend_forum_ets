package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.interfaces.SectionInterface;
import com.example.demo.model.SectionModel;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.UserRepository;

public class SectionService implements SectionInterface {

    @Autowired
    SectionRepository repo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Integer verify(CreateSectionDTO info) {
        
        if (info.description().equals("") || info.title().equals(""))
            return 1;       // campos vazios

        var found = repo.findByTitle(info.title());

        if (!found.isEmpty())
            return 2;       // mesmo nome
        
        
        return 10;

    }

    @Override
    public ArrayList<SectionModel> getSpaces(String name, Integer page, Integer limit) {
        var results = repo.findByTitleContains(name, PageRequest.of(page, limit)); 
        return new ArrayList<>(results);
    }

    @Override
    public SectionDTO createSection(CreateSectionDTO info) {
        SectionModel newSection = new SectionModel();
        newSection.setTitle(info.title());
        newSection.setDescription(info.description());
        newSection.setImage(info.image());

        repo.save(newSection);

        SectionDTO sec = new SectionDTO(newSection.getSectionId(), newSection.getTitle(), newSection.getDescription(), newSection.getImage());
        return sec;
    }
    
}
