package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.UserDataDto;
import com.example.demo.interfaces.UserInterface;

@RestController
@RequestMapping("/register")
public class UserController {

    @Autowired
    UserInterface service;

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody UserDataDto user) {
        
        if (user.name() == null || user.edv() == null || user.email() == null || user.password() == null || user.instructor() == null) {
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        } 

        if (!service.validateEmail(user.email())) {
            return new ResponseEntity<>(1, HttpStatus.BAD_REQUEST);
        }
        
        if (!service.validatePassword(user.password())) {
            return new ResponseEntity<>(2, HttpStatus.BAD_REQUEST);
        }
        
        if (!service.validateEDV(user.edv())) {            
            return new ResponseEntity<>(3, HttpStatus.BAD_REQUEST);
        }
        
        service.Register(user);
        return new ResponseEntity<>(10, HttpStatus.OK);
    }
}
