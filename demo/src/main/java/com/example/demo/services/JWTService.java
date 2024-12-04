package com.example.demo.services;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import com.example.demo.dto.Token;
import com.example.demo.interfaces.JWTInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTService implements JWTInterface<Token>{

    private final String SECRET_KEY = "ouqebfdouieb7ergyudgfq978t8sdghfsfhg87qerueuamoaamorafouqewfnuoqewnhfouewnadasijdadasdafouewnh";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    @Override
    public String generateJWT(Token token) {
        var claims = new HashMap<String, Object>();
        claims.put("userId", token.userId());
        claims.put("instructor", token.instructor());
        return get(claims);
    }

    private String get(HashMap<String, Object> customClaims) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
            .claims()
                .add(customClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .and()
                .signWith(key)
                .compact();
    }

    private HashMap<String, Object> validateJwt(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
        
        return new HashMap<>(claims);
    }

    @Override
    public Token extractInfo(String jwt) {
        try
        {
            var map = validateJwt(jwt);
            System.out.println("token map: " + map);

            Token token = new Token(Long.parseLong(map.get("userId").toString()), Boolean.parseBoolean(map.get("instructor").toString()));
            System.out.println("token: " + token);

            return token;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
