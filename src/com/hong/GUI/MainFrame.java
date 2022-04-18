package com.hong.GUI;

import com.hong.UsersDatabase;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public final static String LOGINPANEL = "Login Card";
    public final static String ACCOUNTPANEL = "Account Card";
    private final UsersDatabase usersDatabase;
    private JPanel cards;

    public MainFrame() {
        usersDatabase = new UsersDatabase();
        usersDatabase.loadData();

        createComponents();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Scuffed Account System");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void createComponents() {
        cards = new JPanel(new CardLayout());
        cards.add(new LoginPanel(usersDatabase, cards), LOGINPANEL);
        cards.add(new AccountPanel(null), ACCOUNTPANEL);
        this.add(cards);
    }

}
