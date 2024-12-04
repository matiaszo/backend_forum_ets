package com.example.demo.interfaces;

public interface JWTInterface<T> {
    public String generateJWT(T token); // generateToken: gerar Token ->  recebe um json (dto <T> Token) e transforma no JWT (string xxx.xxx.xxx)
    public T extractInfo(String jwt);   // extractInfo: extrair informaçoes -> recebe o JWT (string xxx.xxx.xxx) e retorna um json (dto <T> Token) com as infomrmações extraídas
}
