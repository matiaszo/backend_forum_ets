package com.example.demo.services;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.comments.CommentDTO;
import com.example.demo.dto.comments.CommentLike;
import com.example.demo.dto.comments.CreateCommentDTO;
import com.example.demo.interfaces.CommentInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.LikeModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.LikeRepository;
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

    @Autowired
    LikeRepository likeRepo;

    @Override
    public CommentDTO post(CreateCommentDTO data) {

        InteractionModel interaction = new InteractionModel();
        interaction.setType("COMMENT");
        interaction.setDate(new Timestamp(new Date().getTime()));

        var found = userRepo.findById(data.userId());
        if(found.isPresent()) 
        {
            interaction.setUser(found.get());
        }else
        {
            return null;
        }

        CommentModel comment = new CommentModel();
        comment.setContent(data.content());

        var topic = topicRepo.findById(data.topicId());
        if(topic.isPresent())
        {
            comment.setTopic(topic.get());
        }else
        {
            return null;
        }

        interaction = interactionRepo.save(interaction);
        comment.setInteraction(interaction);

        if(data.mentionId() != null)
        {
            var foundComment = commentRepo.findById(data.mentionId());

            if(foundComment.isPresent())
            {
                comment.setMention(foundComment.get());
            }
        }

        comment = commentRepo.save(comment);

        return new CommentDTO(data.userId(), data.topicId(), data.content(), comment.getMention().getId_comment());
    }

    @Override
    public ArrayList<LikeModel> like(CommentLike like) {

        var foundLike = likeRepo.findByCommentAndUser(like.commentId(), like.userId());

        //System.out.println(foundLike.toString());

        if(!foundLike.isEmpty())
        {
            likeRepo.deleteById(foundLike.get(0).getId_like());
            interactionRepo.deleteById(foundLike.get(0).getInteraction().getId_interaction());  
        }else
        {
            LikeModel likeuou = new LikeModel();
            InteractionModel interaction = new InteractionModel();

            var found = userRepo.findById(like.userId());
            if(found.isPresent())
            {
                interaction.setUser(found.get());
            }else
            {
                return null;
            }

            var comment = commentRepo.findById(like.commentId());
            if(comment.isPresent())
            {
                likeuou.setComment(comment.get());
            }else
            {
                return null;
            }
            
            interaction.setDate(new Timestamp(new Date().getTime()));
            interaction.setType("LIKE");
            
            interaction = interactionRepo.save(interaction);
            
            likeuou.setInteraction(interaction);
            
            likeRepo.save(likeuou);
        }
        
        var likes = commentRepo.findById(like.commentId()).get().getLikes();

        //System.out.println(likes);

        return new ArrayList<>(likes);
    }
    
}
