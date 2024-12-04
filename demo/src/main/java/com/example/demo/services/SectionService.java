package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.interfaces.SectionInterface;
import com.example.demo.model.SectionModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.UserRepository;

public class SectionService implements SectionInterface {

    @Autowired
    SectionRepository repo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Integer createSection(CreateSectionDTO info, Long id) {
        
        if (info.description().equals("") || info.title().equals(""))
            return 1;       // campos vazios

        var found = repo.findByTitle(info.title());

        if (!found.isEmpty())
            return 2;       // mesmo nome
        
        UserModel user = userRepo.findById(id).get();

        if (!user.getInstructor()) {
            return 3;       // FORBIDDEN

        }

        SectionModel newSection = new SectionModel();
        newSection.setTitle(info.title());
        newSection.setDescription(info.description());

        repo.save(newSection);
        return 10;

    }

    @Override
    public ArrayList<SectionModel> getSpaces(String name, Integer page, Integer limit) {
        var results = repo.findByTitleContains(name, PageRequest.of(page, limit)); 
        return new ArrayList<>(results);
    }
    
}
