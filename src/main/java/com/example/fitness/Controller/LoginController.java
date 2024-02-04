package com.example.fitness.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.Entity.User;
import com.example.fitness.Service.UserServiceImplementation;

import jakarta.el.ELException;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserServiceImplementation userServiceImplementation;
    
    LoginController(UserServiceImplementation userServiceImplementation){
        this.userServiceImplementation=userServiceImplementation;
    }

    @PostMapping("/login")
    public User userlogin(@RequestBody User userinfo)
    {
        userServiceImplementation.login(userinfo);
        return null;
    }
    
    //The entity used here might be different
    @PostMapping("/Register")
    public ResponseEntity<?> registerNew(@RequestBody User newuserinfo)
    {   
        try{
            String res=userServiceImplementation.createNewUser(newuserinfo);
            return ResponseEntity.ok(res);
    }catch(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
