package com.example.demo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.interfaces.EncoderInterface;

public class EncoderService implements EncoderInterface {

    @Override
    public String encode(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @Override
    public Boolean validate(String password, String encrypt_password) {
        return new BCryptPasswordEncoder().matches(password, encrypt_password);
    }
}
