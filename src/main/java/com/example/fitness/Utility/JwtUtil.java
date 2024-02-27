package com.example.fitness.Utility;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.UUID;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    
    private String secret=UUID.randomUUID().toString();

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function <Claims,T> claimsResolver){
        final Claims claims=Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String generateToken(String email){
        return Jwts.builder().setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 hr validity
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public Boolean validateToken(String token, String email){
        final String extractEmail=extractUsername(token);
        return (extractEmail.equals(email) && !(isTokenExpired(token)));
    }   

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date()); //new Date() - dttm at which the object is initialized
    }
}
