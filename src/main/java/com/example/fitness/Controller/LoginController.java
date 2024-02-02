package com.example.fitness.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.Entity.User;
import com.example.fitness.Entity.User;
import com.example.fitness.Service.UserServiceImplementation;

@RestController("/auth")
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
    public User registerNew(@RequestBody User newuserinfo)
    {
        userServiceImplementation.createNewUser(newuserinfo);
        return null;
    }
}
