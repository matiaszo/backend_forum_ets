package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.LoginResponse;
import com.example.demo.interfaces.AuthInterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

    @Autowired
    AuthInterface authService;

    @PostMapping("/auth")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto info) {
        
        var res = authService.login(info);

        if (res == null)
            return new ResponseEntity<LoginResponse>(res, HttpStatus.UNAUTHORIZED);
        
        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
    }
    
    
}
