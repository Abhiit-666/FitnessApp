package com.example.fitness.Controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fitness.Entity.User;
import com.example.fitness.Service.TokenServiceImplementation;
import com.example.fitness.Service.UserServiceImplementation;

import jakarta.el.ELException;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @Autowired
    TokenServiceImplementation tokenServiceImplementation;
    
    LoginController(UserServiceImplementation userServiceImplementation, TokenServiceImplementation tokenServiceImplementation){
        this.userServiceImplementation=userServiceImplementation;
        this.tokenServiceImplementation=tokenServiceImplementation;
    }

    @PostMapping("/login")
    public ResponseEntity<?> userlogin(@RequestBody User userinfo)
    {
        boolean isAuthenticated= userServiceImplementation.login(userinfo);
        if(isAuthenticated){
            String sesToken= tokenServiceImplementation.generateToken(userinfo.getEmail());
            return ResponseEntity.ok().body(Collections.singletonMap("token",sesToken));
            //completion Required. 
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");        }
       
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
