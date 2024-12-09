package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.dto.topics.TopicDTO;
import com.example.demo.interfaces.TopicInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.TopicModel;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.TopicRepository;

public class TopicService implements TopicInterface {

    @Autowired
    TopicRepository repo;

    @Autowired
    SectionRepository sectionRepo;

    @Autowired
    InteractionRepository interactionRepo;

    @Autowired 
    CommentRepository commentRepo;


    @Override
    public TopicDTO create(CreateTopicDTO info) {

        // cria o tópico
        TopicModel topic = new TopicModel();
        topic.setTitle(info.title());

        // acha a sessão do tópico pelo idSection
        var sec = sectionRepo.findById(info.idSection()).get();
        topic.setSection(sec);
        
        // cria um comentário 
        CommentModel comment = new CommentModel();
        comment.setContent(info.mainComment());
        comment.setTopic(topic);

        // seta o main coment dso novo tópico como o comentário que acabou de criar
        topic.setComment(comment);
        
        // cria uma interação para guardar o comentário
        InteractionModel interaction = new InteractionModel();
        interaction.setDate(null);
        interaction.setUser(null);

        repo.save(topic);
        sectionRepo.save(sec);
        commentRepo.save(comment);
        interactionRepo.save(interaction);

        // deu boa fml só corre rpro abraço
        return new TopicDTO(topic.getId_topic(), topic.getTitle(), topic.getComment().getContent(), topic.getSection().getId());

    }

    @Override
    public Boolean verifyTopicTitle(String title) {
        var found = repo.findByTitle(title);

        if (found == null)
            return false;

        return true;
    }

    @Override
    public Boolean verifyTopicInputs(CreateTopicDTO info) {
        if (info.mainComment().equals("") || info.title().equals(""))
            return false;

        return true;
    }
    
}
