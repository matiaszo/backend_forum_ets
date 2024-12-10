package com.example.demo.interfaces;

import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CreateCommentDTO;

public interface CommentInterface {
    public CommentDTO post(CreateCommentDTO data);

}
