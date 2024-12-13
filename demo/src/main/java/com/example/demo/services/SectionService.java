package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.section.CreateSectionDTO;
import com.example.demo.dto.section.SectionDTO;
import com.example.demo.dto.section.SectionTopicsDTO;
import com.example.demo.dto.topics.TopicDTO;
import com.example.demo.interfaces.SectionInterface;
import com.example.demo.model.SectionModel;
import com.example.demo.model.TopicModel;
import com.example.demo.model.UserModel;
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
    public Integer verify(CreateSectionDTO info)
    {
        if (info.description().equals("") || info.title().equals(""))
            return 1;       // campos vazios

        var found = repo.findByTitle(info.title());

        if (!found.isEmpty())
            return 2;       // mesmo nome
        
        return 10;
    }

    @Override
    public ArrayList<SectionDTO> getSection(String name, Integer page, Integer limit)
    {
        System.out.println("Searching for title: " + name);

        var results = repo.findByTitleContainsIgnoreCase(name, PageRequest.of(page, limit));

        if(results.isEmpty())
        {
            return null;
        }

        return results.stream()
                  .map(this::transformToDTO) 
                  .collect(Collectors.toCollection(ArrayList::new));
    }

    private SectionDTO transformToDTO(SectionModel section)
    {
        return new SectionDTO(
            section.getId(),
            section.getTitle(),
            section.getDescription(),
            section.getImage(),
            section.getCreator().getName()
        );
    }

    @Override
    public SectionDTO createSection(CreateSectionDTO info)
    {
        SectionModel newSection = new SectionModel();
        newSection.setTitle(info.title());
        newSection.setDescription(info.description());
        newSection.setImage(info.image());

        Optional<UserModel> User = userRepo.findById(info.userId());

        if(User.isPresent())
        {
            newSection.setCreator(User.get());
        }else
        {
            return null;
        }

        newSection = repo.save(newSection);

        return new SectionDTO
        (
            newSection.getId(),
            newSection.getTitle(),
            newSection.getDescription(),
            newSection.getImage(),
            newSection.getCreator().getName()
        );
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

        SectionModel section = found.get();

        if (info.title() != null)
            section.setTitle(info.title());

        if (info.description() != null)
            section.setDescription(info.description());

        if (info.image() != null)
            section.setImage(info.image());

        section = repo.save(section);

        return new SectionDTO(id, section.getTitle(), section.getDescription(), section.getImage(), section.getCreator().getName());

    }

    @Override
    public SectionTopicsDTO getSingleSection(Long id) {
        
        var found = repo.findById(id);
        System.out.println(found);

        if (found.isEmpty())
            return null;

        SectionModel section = found.get();

        SectionDTO sec = new SectionDTO(id, section.getTitle(), section.getDescription(), section.getImage(), section.getCreator().getName());

        var topics = section.getTopics();

        var res = topics.stream()
        .map(this::transformTopicToDTO) 
        .collect(Collectors.toCollection(ArrayList::new));

        return new SectionTopicsDTO(sec, new ArrayList<>(res));
    }

    private TopicDTO transformTopicToDTO(TopicModel topic) {
        return new TopicDTO(
        topic.getTopicId(),
        topic.getTitle(),
        topic.getComment().getContent(),
        topic.getSection().getId()
        );
    }
    
}
