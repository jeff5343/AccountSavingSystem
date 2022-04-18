package com.hong.GUI;

import com.hong.User;
import com.hong.UsersManager;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final UsersManager usersManager;

    public MainFrame() {
        usersManager = new UsersManager();
        usersManager.loadData();

        createComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scuffed Account System");
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void createComponents() {
        //this.add(new LoginPanel(usersManager));
        this.add(new AccountPanel(new User("jeff", "jdawd")));
    }

}
