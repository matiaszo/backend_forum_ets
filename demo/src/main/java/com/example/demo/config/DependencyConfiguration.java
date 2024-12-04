package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.Token;
import com.example.demo.filters.JWTFilter;
import com.example.demo.interfaces.AuthInterface;
import com.example.demo.interfaces.EncoderInterface;
import com.example.demo.interfaces.JWTInterface;
import com.example.demo.interfaces.SkillsInterface;
import com.example.demo.interfaces.UserInterface;
import com.example.demo.services.AuthService;
import com.example.demo.services.EncoderService;
import com.example.demo.services.JWTService;
import com.example.demo.services.SkillsService;
import com.example.demo.services.UserService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    public JWTInterface<Token> jwtInterface() {
        return new JWTService();
    }
    
    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }
    
    @Bean
    public AuthInterface authInterface() {
        return new AuthService();
    }
   
    @Bean
    public EncoderInterface encoderInterface() {
        return new EncoderService();
    }
   
    @Bean
    public UserInterface userInterface() {
        return new UserService();
    }
   
    @Bean
    public SkillsInterface skillInterface() {
        return new SkillsService();
    }
}
