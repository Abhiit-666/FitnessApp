package com.example.fitness.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.LoginRepository;
import com.example.fitness.Entity.User;
import com.example.fitness.Entity.User;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    LoginRepository loginRepository;

    UserServiceImplementation(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }
 
   public String login(User user){

        if(loginRepository.checkUserToLogin(user)){
        return "Logged in";
        } 
        else{
        return "User details Wrong";
        }
    }

    public String createNewUser(User newuser){
        if(!loginRepository.checkUserPresent(newuser.getEmail())){
            loginRepository.createNewUser(newuser);
        }
        String email=newuser.getEmail();

        return null;
    }


}
