package com.example.fitness.OAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.fitness.Service.UserServiceImplementation;


@EnableWebSecurity
public class SecurityConfig {

    private UserServiceImplementation userServiceImplementation;

    public SecurityConfig(UserServiceImplementation userServiceImplementation)
    {
        this.userServiceImplementation=userServiceImplementation;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/","/home").permitAll()
                .anyRequest().authenticated())
            .formLogin((formLogin)-> formLogin //Figure out how to make this an UI page
                .loginPage("/login")
                .permitAll())
            .logout((logout)->logout.permitAll())
            .userDetailsService(userServiceImplementation);   

        return http.build();
    }
}
