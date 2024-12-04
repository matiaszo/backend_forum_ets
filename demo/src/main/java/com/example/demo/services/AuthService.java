package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.Token;
import com.example.demo.dto.auth.LoginDto;
import com.example.demo.interfaces.AuthInterface;
import com.example.demo.interfaces.EncoderInterface;
import com.example.demo.interfaces.JWTInterface;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.UserRepository;

public class AuthService implements AuthInterface {

    @Autowired
    UserRepository userRepo;

    @Autowired
    EncoderInterface encoder;

    @Autowired
    JWTInterface<Token> jwtService;

    @Override
    public String login(LoginDto info) {
        UserModel user = userRepo.findByEdv(info.edv()).get(0);
        
        // verifica se os campos estão preenchidos
        if (!verifyFieldsLogin(info)) 
            return null;
        
        // verifica s eo usuário foi achado no banco
        if (user == null)           
            return null;

        // verifica se as senhas conferem
        if (!encoder.validate(info.password(), user.getPassword()))
            return null;

        // cria um novo token e transforma-o em JWT (string)
        Token newToken = new Token(user.getUserId(), user.getInstructor());

        String jwt = jwtService.generateJWT(newToken);

        return jwt;

    }

    
    public Boolean verifyFieldsLogin(LoginDto loginData) {
        return !loginData.password().equals("") || loginData.edv().equals("");
    }
}

