package com.ana.dalmolin.gestao_vagas.provides;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JWTProvider {
    
    @Value("${security.token.secret}")
    private String secreteKey;


    public String validateToken(String token){
        // token = token.replace("Bearer ", "");
        
        Algorithm algorithm = Algorithm.HMAC256(secreteKey);

        try {
            var subject = JWT.require(algorithm)
            .build()
            .verify(token)
            .getSubject();
            return subject;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "";
        }
    }

    // public String generateToken(String email){
    //     return "token";
    // }
}
