package com.example.lenovo.retdata;

public class User {


    private String sender;
    private String reciver;

    public User() {

    }

    public User(String sender, String reciver) {
        this.sender = sender;
        this.reciver = reciver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }
}
