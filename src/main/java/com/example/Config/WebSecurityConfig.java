package com.example.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import com.example.fitness.Service.UserServiceImplementation;

public class WebSecurityConfig extends WebSecurityConfiguration {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private JwtRequestFilter JwtRequestFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
    }

    
}
