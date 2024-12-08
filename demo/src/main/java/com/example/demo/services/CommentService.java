package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CreateCommentDTO;
import com.example.demo.interfaces.CommentInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.repositories.UserRepository;

public class CommentService implements CommentInterface {

    @Autowired
    UserRepository userRepo;

    @Autowired
    InteractionRepository interactionRepo;

    @Autowired
    TopicRepository topicRepo;

    @Autowired
    CommentRepository commentRepo;

    @Override
    public CommentDTO post(CreateCommentDTO data) {

        InteractionModel interaction = new InteractionModel();
        interaction.setType("COMMENT");

        var found = userRepo.findById(data.userId());
        if (found.isPresent()) 
            interaction.setUser(found.get());

        interactionRepo.save(interaction);

        CommentModel comment = new CommentModel();
        comment.setContent(data.content());
        comment.setInteraction(interaction);
        
        var topic = topicRepo.findById(data.topicId());

        if (topic.isPresent()) 
            comment.setTopic(topic.get());        

        if (data.mentionId() != null) {
            var foundComment = commentRepo.findById(data.mentionId());

            if (foundComment.isPresent())
                comment.setMention(foundComment.get());

        }

        commentRepo.save(comment);

        return new CommentDTO(data.userId(), data.topicId(), data.content(), comment.getMention().getId_comment());
    }
    
}
