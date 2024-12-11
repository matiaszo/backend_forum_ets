package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CommentLike;
import com.example.demo.dto.comments.CreateCommentDTO;
import com.example.demo.interfaces.CommentInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.repositories.CommentRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    CommentInterface service;

    @Autowired
    CommentRepository repo;

    @PostMapping("/")
    public ResponseEntity<CommentDTO> postMethodName(@RequestBody CreateCommentDTO data) {
        
        var post = service.post(data);
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @PostMapping("/like")
    public ResponseEntity<Integer> like(@RequestBody CommentLike like) {
        
        var likes = service.like(like);
        return new ResponseEntity<>(likes.size(), HttpStatus.OK);
    }
    
    @DeleteMapping("/{idComment}")
    public ResponseEntity<Integer> deleteComment(@RequestAttribute Token token, @PathVariable Long idComment) {
        CommentModel model = repo.findById(idComment).get();

        if (token.userId() != model.getInteraction().getUser().getId_user()) 
            return new ResponseEntity<>(1, HttpStatus.FORBIDDEN);
        
        repo.deleteById(model.getId_comment());

        return new ResponseEntity<>(10, HttpStatus.OK);
    }
}
