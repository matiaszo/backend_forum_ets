package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CommentLike;
import com.example.demo.dto.comments.CreateCommentDTO;
import com.example.demo.interfaces.CommentInterface;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    CommentInterface service;

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
    
}
