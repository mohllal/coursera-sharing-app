package com.example.sharingapp;

import java.util.UUID;

public class Contact extends Observable {
    private String username;
    private String email;
    private String id;

    public Contact(String username, String email, String id) {
        this.username = username;
        this.email = email;

        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

    public String getId(){
        return this.id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
        super.notifyObservers();
    }

    public void updateId(String id){
        this.id = id;
        super.notifyObservers();
    }

    public void setUsername(String username) {
        this.username = username;
        super.notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
        super.notifyObservers();
    }

    public String getEmail() {
        return email;
    }
}
