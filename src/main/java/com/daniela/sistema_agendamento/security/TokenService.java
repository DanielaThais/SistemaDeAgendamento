package com.daniela.sistema_agendamento.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    private final String chave = "security-key-sistema-agendamento";

    public String gerarToken(String email){
        SecretKey secretkey = Keys.hmacShaKeyFor(
                chave.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(secretkey)
                .compact();
    }

    public String extrairEmail(String token){
        SecretKey secretkey = Keys.hmacShaKeyFor(
                chave.getBytes(StandardCharsets.UTF_8)
        );
        return Jwts.parser()
                .verifyWith(secretkey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
