package com.example.fitness.Config.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fitness.Service.UserServiceImplementation;
import com.example.fitness.Utility.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private JwtUtil jwtUtil;
}
