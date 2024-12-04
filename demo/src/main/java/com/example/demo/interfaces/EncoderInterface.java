package com.example.demo.interfaces;

public interface EncoderInterface {
    public String encode(String password);                                  // para codificar uma senha
    public Boolean validate(String password, String encrypt_password);      // para validar se a senha passada pelo usuário coincide com a senha criptografada no banco
}

