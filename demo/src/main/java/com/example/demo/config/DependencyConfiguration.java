package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.Token;
import com.example.demo.interfaces.JWTInterface;
import com.example.demo.services.JWTService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    public JWTInterface<Token> jwtInterface() {
        return new JWTService();
    }
}
