package com.example.app;

import java.io.Serializable;

public class Rating implements Serializable {
    public User  user;
    public int rating;
    public Rating(User user,int rating){
        this.rating=rating;
        this.user=user;
    }
    public User getUser(){
        return user;
    }
    public double getRating(){
        return rating;
    }

}
