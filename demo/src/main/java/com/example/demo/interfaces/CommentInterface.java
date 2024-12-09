package com.example.demo.interfaces;

import java.util.ArrayList;

import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CommentLike;
import com.example.demo.dto.comments.CreateCommentDTO;
import com.example.demo.model.LikeModel;

public interface CommentInterface {
    public CommentDTO post(CreateCommentDTO data);
    public ArrayList<LikeModel> like(CommentLike like);

}
