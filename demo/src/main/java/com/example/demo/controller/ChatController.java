package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.chat.ChatDataDto;
import com.example.demo.dto.chat.ChatMessagesDto;
import com.example.demo.dto.chat.MessageDataDto;
import com.example.demo.dto.chat.NameChatDto;
import com.example.demo.dto.message.MessageBodyDto;
import com.example.demo.dto.user.UserCommentDto;
import com.example.demo.interfaces.ChatInterface;
import com.example.demo.model.ChatModel;
import com.example.demo.model.InteractionModel;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.InteractionRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    ChatInterface service;

    @Autowired
    ChatRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    MessageRepository messageRepo;

    @Autowired
    InteractionRepository interRepo;

    @PostMapping
    public ResponseEntity<ChatDataDto> create(@RequestAttribute Token token, @RequestBody NameChatDto name) {

        if (!token.instructor())
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);

        if (!service.validateName(name.name()))
            return new ResponseEntity<>(null, HttpStatus.FOUND);

        ChatModel model = new ChatModel();
        model.setName(name.name());

        repo.save(model);
        return new ResponseEntity<>(new ChatDataDto(model.getId_chat(), model.getName()), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> createMessage(@RequestAttribute Token token, @PathVariable Long id, @RequestBody MessageBodyDto message)
    {    
        MessageModel model = new MessageModel();

        Optional<ChatModel> Chat = repo.findById(id);
        if(Chat.isEmpty())
        {
            return new ResponseEntity<>("O chat da requisição não existe", HttpStatus.NOT_FOUND);
        }

        Optional<UserModel> User = userRepo.findById(token.userId());
        if(User.isEmpty())
        {
            return new ResponseEntity<>("O usuário não existe", HttpStatus.NOT_FOUND);
        }

        model.setChat(Chat.get());
        model.setText(message.text());
        
        InteractionModel inter = new InteractionModel();
        inter.setType(null);
        inter.setUser(User.get());
        inter.setDate(new Timestamp(new Date().getTime()));

        inter = interRepo.save(inter);
        model.setInteraction(inter);

        messageRepo.save(model);
        return new ResponseEntity<>(User.get().getName() + " mandou uma mensagem", HttpStatus.OK);
    }


    private ChatDataDto transformToDTO(ChatModel section) {
        return new ChatDataDto(
            section.getId_chat(),
            section.getName()

        
        );
    }

    @GetMapping
    public List<ChatDataDto> getchats(@RequestAttribute Token token, Integer page, String name) {

        var results = repo.findAll();
            return results.stream()
                    .map(this::transformToDTO) 
                    .collect(Collectors.toCollection(ArrayList::new));
                  // List<ChatModel> chats;
              
                  // if (name != null) {
                  //     chats = repo.findByNameContains(name);
                  //     System.out.println(chats);
                  // } else {
                  //     chats = repo.findByNameContains("");
                  //     System.out.println(chats);
                  // }
              
              
                  // Integer countModel = 0;
                  // Integer countPage = 1;
              
                  // List<ChatDataDto> returnDto = new ArrayList<>();
              
                  // for (ChatModel chatModel : chats) {
                      
                  //     if (countModel == 10) {
                  //         countPage++;
                  //         countModel = 0;
                  //     }
              
                  //     if (countPage == page)
                  //         returnDto.add(new ChatDataDto(chatModel.getId_chat(), chatModel.getName()));
                      
              
                  //     countModel++;
                  // }
    }

    @GetMapping("/{idchat}")
    public ChatMessagesDto getMessages(@PathVariable Long idchat) {

        ChatModel model = repo.findById(idchat).get();

        List<MessageDataDto> messages = new ArrayList<>();

        List<MessageModel> listMessages = messageRepo.findByChat(idchat);

        for (MessageModel messageModel : listMessages) {
            messages.add(new MessageDataDto(messageModel.getId_message(), 
            messageModel.getInteraction().getDate(),
            messageModel.getText(), 
            new UserCommentDto(messageModel.getInteraction().getUser().getId_user(), messageModel.getInteraction().getUser().getName(), messageModel.getInteraction().getUser().getInstructor(), messageModel.getInteraction().getUser().getImage())));
        }

        return new ChatMessagesDto(idchat, model.getName(), messages);
    }
}
