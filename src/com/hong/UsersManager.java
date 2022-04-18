package com.hong;

import java.io.*;
import java.util.ArrayList;

public class UsersManager implements Serializable {

    private static long serialVersionUID = 8600091809369890560L;
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isUsernameTaken(String username) {
        return getUserWithUsername(username)!=null;
    }

    public boolean isPasswordCorrect(User inputUser) {
        User user = getUserWithUsername(inputUser.getUsername());
        if(user!=null) {
            if(user.getPassword().equals(inputUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void saveData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("UsersInfo.ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            FileInputStream fileInput = new FileInputStream("UsersInfo.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            UsersManager manager = (UsersManager)objectInput.readObject();
            users = manager.getUsers();
            objectInput.close();
            fileInput.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void printInfo() {
        for(User u : users) {
            u.printUserInfo();
        }
    }

    /**
     * @return
     * returns null if user with that
     * username cannot be found
     */
    private User getUserWithUsername(String username) {
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
