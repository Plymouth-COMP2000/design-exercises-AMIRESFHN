package com.example.resapp.model;

public class UserRequest {
    public String username;
    public String password;
    public String firstname;
    public String lastname;
    public String email;
    public String contact;
    public String usertype;

    public UserRequest(String username, String password,
                       String firstname, String lastname,
                       String email, String contact,
                       String usertype) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.contact = contact;
        this.usertype = usertype;
    }
}

