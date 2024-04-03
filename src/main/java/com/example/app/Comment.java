package com.example.app;

import java.io.Serializable;

public class Comment implements Serializable {
    public String comment;
    public User user;

    public Comment(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }
    public String  getUsernamecom(){
        return user.getUsername();
    }
    public String getComment(){
        return comment;
    }


    
}
