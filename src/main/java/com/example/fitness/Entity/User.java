package com.example.fitness.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.*;

@Entity
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userFirstName;
    private String userSecondName;
    private int age;
    private String height;
    private String weight;
    private String goal;
    private String password;

    @ManyToMany
    @JoinTable(
        name="user_friends",//name of the join Table
        joinColumns=@JoinColumn(name="user_id"),//User_id column
        inverseJoinColumns = @JoinColumn(name="friend_id")// Column for the friends of the user
    )
    private List<User> friends;
    
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(
        name="user_roles", //name of table that stores the user and its roles
        joinColumns = @JoinColumn(name= "user_id"),//entity owning the association
        inverseJoinColumns = @JoinColumn(name="role_id") //entity on the other side of the association
    )
    private Set<Role> roles;
    
    
    /**
     * @return String return the userFirstName
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * @param userFirstName the userFirstName to set
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * @return String return the userSecondName
     */
    public String getUserSecondName() {
        return userSecondName;
    }

    /**
     * @param userSecondName the userSecondName to set
     */
    public void setUserSecondName(String userSecondName) {
        this.userSecondName = userSecondName;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return String return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return String return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return String return the goal
     */
    public String getGoal() {
        return goal;
    }

    /**
     * @param goal the goal to set
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * @return List<User> return the friends
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * @param friends the friends to set
     */
    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    /**
     * @return Set<Role> return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
