package com.example.rishabh.myapplication;

public class User {
    private static User self = new User();
    private String SJSUID = "";
    private String firstName = "";

    public void setID(String id){
        SJSUID = id;
    }
    public void setName(String name){
        firstName = name;
    }
    public static User get(){
        return self;
    }
    public String getID(){
        return SJSUID;
    }
    public String getName(){
        return firstName;
    }
}

