package com.hong;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void printUserInfo() {
        System.out.println("username: " + getUsername() + ", password: " + getPassword());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
