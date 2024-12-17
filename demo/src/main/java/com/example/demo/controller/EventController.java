package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Token;
import com.example.demo.dto.event.EventDto;
import com.example.demo.dto.event.NewEventDto;
import com.example.demo.dto.user.UserSimplifiedDto;
import com.example.demo.model.EventModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.UserRepository;

@RestController
public class EventController
{
    @Autowired
    EventRepository EventRep;

    @Autowired
    UserRepository UserRep;

    @GetMapping("/event")
    public ResponseEntity<List<EventDto>> GetEvents(@RequestAttribute Token token)
    {
        List<EventModel> Results = EventRep.findEvents();
        List<EventDto> Ret = new ArrayList<>();

        Results.forEach((item) ->
        {
            Ret.add(new EventDto
            (
                item.getId_event(),
                item.getType(),
                item.getText(),
                item.getImage(),
                item.getTitle(),
                new UserSimplifiedDto
                (
                    item.getUser().getId_user(),
                    item.getUser().getName(),
                    item.getUser().getImage(),
                    item.getUser().getInstructor()
                )
            ));
        });

        return new ResponseEntity<>(Ret, HttpStatus.OK);
    }

    @GetMapping("/news")
    public ResponseEntity<List<EventDto>> GetNews(@RequestAttribute Token token)
    {
        List<EventModel> Results = EventRep.findNews();
        List<EventDto> Ret = new ArrayList<>();

        Results.forEach((item) ->
        {
            Ret.add(new EventDto
            (
                item.getId_event(),
                item.getType(),
                item.getText(),
                item.getImage(),
                item.getTitle(),
                new UserSimplifiedDto
                (
                    item.getUser().getId_user(),
                    item.getUser().getName(),
                    item.getUser().getImage(),
                    item.getUser().getInstructor()
                )
            ));
        });

        return new ResponseEntity<>(Ret, HttpStatus.OK);
    }

    @GetMapping()

    @PostMapping("/event")
    public ResponseEntity<String> CreateEvent(@RequestAttribute Token token, @RequestBody NewEventDto EventData)
    {
        if(token.instructor())
        {
            Optional<UserModel> User = UserRep.findById(token.userId());
            if(User.isPresent())
            {
                EventModel Event = new EventModel();
        
                Event.setImage(EventData.image());
                Event.setText(EventData.text());
                Event.setTitle(EventData.title());
                Event.setUser(User.get());
                Event.setType("EVENT");
        
                EventRep.save(Event);
                return new ResponseEntity<>("Evento criado com sucesso", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/news")
    public ResponseEntity<String> CreateNews(@RequestAttribute Token token, @RequestBody NewEventDto EventData)
    {
        if(token.instructor())
        {
            Optional<UserModel> User = UserRep.findById(token.userId());
            if(User.isPresent())
            {
                EventModel Event = new EventModel();
        
                Event.setImage(EventData.image());
                Event.setText(EventData.text());
                Event.setTitle(EventData.title());
                Event.setUser(User.get());
                Event.setType("News");
        
                EventRep.save(Event);
                return new ResponseEntity<>("Notícia criada com sucesso", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}