package com.hong.gui;

import com.hong.UsersDatabase;
import com.hong.gui.panels.CardPanel;

import javax.swing.*;

public class MainFrame extends JFrame {

    private final UsersDatabase usersDatabase;

    public MainFrame() {
        usersDatabase = UsersDatabase.getInstance();
        usersDatabase.loadData();

        createComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scuffed Account System");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    private void createComponents() {
        this.add(CardPanel.getInstance());
    }

}
