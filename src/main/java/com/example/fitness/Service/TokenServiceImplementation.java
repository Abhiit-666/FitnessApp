package com.example.fitness.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TokenServiceImplementation implements TokenService {
    
    private Map<String, String> tokenStore= new ConcurrentHashMap<>();

    public String generateToken(String email){
        String token=UUID.randomUUID().toString();
        tokenStore.put(token,email);
        return token;
    }

    public Boolean validateToken(String token){
        return tokenStore.containsKey(token);
    }

    public void removeToken(String token){
        tokenStore.remove(token);
    }

}
