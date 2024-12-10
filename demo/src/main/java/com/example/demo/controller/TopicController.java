package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.comment.MentionDto;
import com.example.demo.dto.comments.CommentDataDto;
import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.CreateTopicFullInfoDTO;
import com.example.demo.dto.topics.TopicCreationResponseDTO;
import com.example.demo.dto.topics.TopicDataDto;
import com.example.demo.dto.user.UserCommentDto;
import com.example.demo.model.CommentModel;
import com.example.demo.model.TopicModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.LikeRepository;
import com.example.demo.repositories.TopicRepository;
import com.example.demo.interfaces.TopicInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    LikeRepository LikeRep;
    
    @PostMapping
    public ResponseEntity<TopicCreationResponseDTO> create(@RequestAttribute("token") Token token, @RequestBody CreateTopicDTO info) {

        //! Possivelmente deletar este if, para qualquer um poder abrir um tópico
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
    public ResponseEntity<TopicDataDto> getComments(@PathVariable Long idTopic, Integer page)
    {
        Optional<TopicModel> Topic = repo.findById(idTopic);

        if(page == null)
        {
            page = 1;
        }
        
        if(Topic.isPresent())
        {
            List<CommentDataDto> Comments = new ArrayList<>();

            TopicModel T = Topic.get();

            CommentModel C = new CommentModel();
            TopicModel Tp = new TopicModel();
            Tp.setTopicId(idTopic);
            C.setTopic(Tp);

            Page<CommentModel> AllComments = comRepo.findAll(Example.of(C), PageRequest.of(page, 20));

            AllComments.get().forEach((item) ->
            {
                UserModel Usr = item.getInteraction().getUser();
                CommentModel Mention = item.getMention();
                Comments.add(new CommentDataDto
                (
                    item.getId_comment(),
                    item.getContent(),
                    LikeRep.countFromComment(item.getId_comment()),
                    new UserCommentDto
                    (
                        Usr.getId_user(),
                        Usr.getName(),
                        Usr.getInstructor(),
                        Usr.getImage()
                    ),
                    new MentionDto
                    (
                        Mention.getId_comment(), 
                        Mention.getInteraction().getUser().getName(), 
                        Mention.getContent()
                    )
                ));
            });

            CommentModel MainComment = T.getComment();
            UserModel Usr = MainComment.getInteraction().getUser();
            return new ResponseEntity<>
            (
                new TopicDataDto
                (
                    idTopic,
                    T.getTitle(),
                    T.getSection().getId(),
                    new CommentDataDto
                    (
                        MainComment.getId_comment(),
                        MainComment.getContent(),
                        LikeRep.countFromComment(MainComment.getId_comment()),
                        new UserCommentDto
                        (
                            Usr.getId_user(),
                            Usr.getName(),
                            Usr.getInstructor(),
                            Usr.getImage()
                        ),
                        null),
                    Comments
                ), 
                HttpStatus.OK
            );
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
