package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.topics.CreateTopicDTO;
import com.example.demo.interfaces.TopicInterface;
import com.example.demo.model.CommentModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.TopicModel;
import com.example.demo.repositories.SectionRepository;
import com.example.demo.repositories.TopicRepository;

public class TopicService implements TopicInterface {

    @Autowired
    TopicRepository repo;

    @Autowired
    SectionRepository sectionRepo;

    @Override
    public Integer create(CreateTopicDTO info) {

        // verifica se já existe tópico com esse nome
        var found = repo.findByTitle(info.title());

        if (found == null)
            return 1;

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

        // deu boa fml só corre rpro abraço
        return 10;


    }
    
}
