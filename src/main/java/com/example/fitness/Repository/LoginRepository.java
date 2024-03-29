package com.example.fitness.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.fitness.Entity.User;

import java.util.*;

@Repository
public class LoginRepository {
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    LoginRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    //Check if the details used for login are valid.
    public Boolean checkUserToLogin(User user){
        String email=user.getEmail();
        String Pwd=user.getPassword();

        String sql="Select count(*) from user where email = :email and password = :password";
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("password", Pwd);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        //put this inside try catch instead of count.
        int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        if(count>0)
        {
            return true;
        }else{
            return false;
        }

    }

    //Get user by username
    public User findByEmail(String username){
        String sql="Select * from user where email= :email";
        Map<String,Object> params =new HashMap<>();
        params.put("email",username);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
        try{
        User user=namedParameterJdbcTemplate.queryForObject(sql, params,User.class);
        return user;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Check if the new email used for creating account already has an account
    public Boolean checkUserPresent(String email){
        String sql="Select count(*) from user where email=:email";
        Map<String, Object> param= new HashMap<>();
        param.put("email",email);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate);
        int count =namedParameterJdbcTemplate.queryForObject(sql,param, Integer.class);
        if(count>0){
            return true;
        }
        return false;
    }

    public String createNewUser(User newuser){
        String email=newuser.getEmail();
        String FName=newuser.getEmail();
        String SName=newuser.getEmail();
        int age=newuser.getAge();
        String height=newuser.getHeight();
        String weight=newuser.getWeight();
        String goal=newuser.getGoal();
        String password=newuser.getPassword();

        String sql="INSERT into user (email, user_first_name, user_second_name, age, height, weight, goal, password) VALUES(?,?,?,?,?,?,?,?)";
        
        try{
        jdbcTemplate.update(sql, email,FName,SName,age,height,weight,goal,password);
        }catch(Exception e){
            e.printStackTrace();
            return "Error in INSERT";
        }
        return "Created";
    }



}
