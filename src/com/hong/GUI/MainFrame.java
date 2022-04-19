package com.hong.GUI;

import com.hong.UsersDatabase;
import javax.swing.*;

public class MainFrame extends JFrame {

    private final UsersDatabase usersDatabase;

    public MainFrame() {
        usersDatabase = new UsersDatabase();
        usersDatabase.loadData();

        createComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scuffed Account System");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    private void createComponents() {
        this.add(new CardPanel(usersDatabase));
    }

}
