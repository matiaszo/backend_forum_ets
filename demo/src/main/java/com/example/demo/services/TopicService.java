package com.example.demo.services;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.CreateTopicFullInfoDTO;
import com.example.demo.dto.topics.TopicDTO;
import com.example.demo.interfaces.TopicInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.TopicModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.UserRepository;

public class TopicService implements TopicInterface {

    @Autowired
    TopicRepository repo;

    @Autowired
    SectionRepository sectionRepo;

    @Autowired
    InteractionRepository interactionRepo;
    
    @Autowired 
    CommentRepository commentRepo;

    @Autowired
    UserRepository userRepo;
    
    
    @Override
    public TopicDTO create(CreateTopicFullInfoDTO info) {
        
        TopicModel topic = new TopicModel();
        repo.save(topic); 

        InteractionModel interaction = new InteractionModel();
        interaction.setUser(null); 
        interaction.setType("COMMENT");
        interaction.setDate(new Timestamp(new Date().getTime()));
    
        var user = userRepo.findById(info.id());
        if (user.isPresent()) {
            var useruou = user.get();
            interaction.setUser(useruou);
        } else {
            interaction.setUser(null);
        }
    
        interactionRepo.save(interaction); 

        CommentModel comment = new CommentModel();
        comment.setContent(info.info().mainComment());
        comment.setTopic(topic);
        comment.setInteraction(interaction); 
        commentRepo.save(comment); 
    
        var sec = sectionRepo.findById(info.info().idSection()).get();
        topic.setSection(sec);
        topic.setTitle(info.info().title());
        topic.setComment(comment);
    
        sectionRepo.save(sec);
        repo.save(topic); 
    
        return new TopicDTO(topic.getId_topic(), topic.getTitle(), topic.getComment().getContent(), topic.getSection().getId());

    }

    @Override
    public Boolean verifyTopicTitle(String title) {
        var found = repo.findByTitle(title);

        if (found == null)
            return true;

        return false;
    }

    @Override
    public Boolean verifyTopicInputs(CreateTopicDTO info) {
        if (info.mainComment().equals("") || info.title().equals(""))
            return false;

        return true;
    }
    
}
