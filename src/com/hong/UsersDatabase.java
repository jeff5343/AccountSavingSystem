package com.hong;

import java.io.*;
import java.util.ArrayList;

public class UsersDatabase implements Serializable {

    private static final UsersDatabase instance = new UsersDatabase();
    public static UsersDatabase getInstance() {
        return instance;
    }
    private UsersDatabase() {}

    private static final long serialVersionUID = 8600091809369890560L;
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
            return user.getPassword().equals(inputUser.getPassword());
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
            UsersDatabase manager = (UsersDatabase)objectInput.readObject();
            users = manager.getUsers();
            objectInput.close();
            fileInput.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserFromDatabase(User user) {
        for(User u : users) {
            if(u.equals(user)) {
                return u;
            }
        }
        return null;
    }

    public void removeUserFromDatabase(User user) {
        users.remove(user);
    }

    public void clearData() {
        users.clear();
        saveData();
    }

    public void printInfo() {
        for(User u : users) {
            System.out.println(u.toString());
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
