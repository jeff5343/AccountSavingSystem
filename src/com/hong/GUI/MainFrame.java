package com.hong.GUI;

import com.hong.UsersManager;

import javax.swing.*;

public class MainFrame extends JFrame {

    private UsersManager usersManager;

    public MainFrame() {
        usersManager = new UsersManager();
        usersManager.loadData();

        createComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createComponents() {
        this.add(new LoginPanel(usersManager));
    }

}
