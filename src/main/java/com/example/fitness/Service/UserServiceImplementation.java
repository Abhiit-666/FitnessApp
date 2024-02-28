package com.example.fitness.Service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.fitness.Entity.User;
import com.example.fitness.Repository.LoginRepository;

import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserDetailsService{

    @Autowired
    LoginRepository loginRepository;

    UserServiceImplementation(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }
 
   public Boolean login(User user){
        if(loginRepository.checkUserToLogin(user)){
        return true;
        } 
        else{
        return false;
        }
    }

    public String createNewUser(User newuser){
        if(!loginRepository.checkUserPresent(newuser.getEmail())){
            loginRepository.createNewUser(newuser);
            return "New User Created";
        }

        return "Already and user, try to login";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User user=loginRepository.findByEmail(username);
            
        if(user == null){
            throw new UsernameNotFoundException("User not found with email: "+username);
        }
        var authorities= user.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toSet());
        
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
        
    }


}
