package com.example.demo.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.dto.topics.TopicDTO;
import com.example.demo.interfaces.SectionInterface;
import com.example.demo.model.SectionModel;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.UserRepository;

public class SectionService implements SectionInterface {

    @Autowired
    SectionRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    TopicRepository topicRepo;

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
    public ArrayList<SectionDTO> getSection(String name, Integer page, Integer limit) {
        var results = repo.findByTitleContains(name, PageRequest.of(page, limit)); 
         return results.stream()
                  .map(this::transformToDTO) 
                  .collect(Collectors.toCollection(ArrayList::new));
    }

    private SectionDTO transformToDTO(SectionModel section) {
        return new SectionDTO(
            section.getId(),
            section.getTitle(),
            section.getDescription(),
            section.getImage(),
            section.getCreator().getName()
        );
    }

    @Override
    public SectionDTO createSection(CreateSectionDTO info) {
        SectionModel newSection = new SectionModel();
        newSection.setTitle(info.title());
        newSection.setDescription(info.description());
        newSection.setImage(info.image());
        newSection.setCreator(null);

        repo.save(newSection);

        SectionDTO sec = new SectionDTO(newSection.getId(), newSection.getTitle(), newSection.getDescription(), newSection.getImage(), newSection.getCreator().getName());
        return sec;
    }

    @Override
    public SectionDTO delete(Long id) {
        var found = repo.findById(id);

                if (found.isEmpty())
                    return null;

                SectionModel sct = found.get(); 

                repo.delete(sct);
                return new SectionDTO(id, sct.getTitle(), sct.getDescription(), sct.getImage(), sct.getCreator().getName());
    }

    @Override
    public SectionDTO update(Long id, CreateSectionDTO info) {
        var found = repo.findById(id);

        if (found.isEmpty())
            return null;

        if (info.title() != null)
            found.get().setTitle(info.title());

        if (info.description() != null)
        found.get().setDescription(info.description());

        if (info.image() != null)
        found.get().setImage(info.image());

        repo.save(found.get());

        return new SectionDTO(id, found.get().getTitle(), found.get().getDescription(), found.get().getImage(), found.get().getCreator().getName());

    }

    // @Override
    // public ArrayList<TopicDTO> getTopics(Long id) {

        // ! FALTA IMPLEMENTAR

        // var found = repo.findById(id);

        // if (found.isEmpty())
        //     return null;
            
        // var topics = topicRepo.findBySectionId(id);

        // System.out.println(topics);

        // return null;
    // }

    @Override
    public ArrayList<TopicDTO> getSingleSection(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingleSection'");
    }
    
}
