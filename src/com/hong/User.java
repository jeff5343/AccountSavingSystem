package com.hong;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 8603625382639890560L;
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return("User[username: " + getUsername() + ", password: " + getPassword() + "]");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null) {
            return false;
        }
        if(!(obj instanceof User)) {
            return false;
        }
        User u = (User) obj;
        return username.equals(u.getUsername()) && password.equals(u.getPassword());
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
