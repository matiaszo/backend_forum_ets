package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.comment.CommentDataDto;
import com.example.demo.dto.comment.MentionDto;
import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.CreateTopicFullInfoDTO;
import com.example.demo.dto.topics.TopicCreationResponseDTO;
import com.example.demo.dto.topics.TopicDataDto;
import com.example.demo.dto.user.UserCommentDto;
import com.example.demo.model.CommentModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.interfaces.TopicInterface;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicInterface service;

    @Autowired
    CommentRepository comRepo;

    @Autowired
    TopicRepository repo;
    
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
    
    @GetMapping("/{idTopic}")
    public TopicDataDto getComments(@PathVariable Long idTopic, Integer page) {
        var topicCurrent = repo.findById(idTopic).get();

        var allComments = comRepo.findByTopic(topicCurrent.getComment().getId_comment(), idTopic);

        List<CommentDataDto> comments = new ArrayList<>();

        MentionDto mention = null;

        Integer countPage = 0;
        Integer countTopic = 0;
        for (CommentModel model : allComments) {
            
            if (countTopic==10)
                countPage++;
                
            if (page == countPage)
                if (model.getMention() != null )
                    mention = new MentionDto(model.getMention().getId_comment(), model.getMention().getInteraction().getUser().getName(), model.getMention().getContent());
    
                comments.add(new CommentDataDto(model.getId_comment(), model.getContent(), new UserCommentDto(model.getInteraction().getUser().getId_user(), model.getInteraction().getUser().getName(), model.getInteraction().getUser().getInstructor(), model.getInteraction().getUser().getImage()), mention));
            
            countTopic++;
        }

        return new TopicDataDto(topicCurrent.getId_topic(), 
            topicCurrent.getTitle(), topicCurrent.getSection().getId(), 
            new CommentDataDto(topicCurrent.getComment().getId_comment(), topicCurrent.getComment().getContent(), 
            new UserCommentDto(topicCurrent.getComment().getInteraction().getUser().getId_user(), topicCurrent.getComment().getInteraction().getUser().getName(), topicCurrent.getComment().getInteraction().getUser().getInstructor(), topicCurrent.getComment().getInteraction().getUser().getImage()), null), 
            comments);
    }
}
