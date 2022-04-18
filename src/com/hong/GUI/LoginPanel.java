package com.hong.GUI;

import com.hong.User;
import com.hong.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private UsersManager usersManager;

    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public LoginPanel(UsersManager usersManager) {
        this.usersManager = usersManager;
        createComponents();
        this.setLayout(new GridLayout(3,2, 10,10));
    }

    private void createComponents() {
        JLabel usernameLabel = new JLabel("username:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("password:", SwingConstants.CENTER);

        usernameField = new JTextField();
        passwordField = new JTextField(); // change to JPasswordField soon

        loginButton = new JButton("login");
        signUpButton = new JButton("sign up");
        loginButton.addActionListener(new LogInButtonAction());
        signUpButton.addActionListener(new SignUpButtonAction());

        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(loginButton);
        this.add(signUpButton);
    }

    private void clearTextFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private class LogInButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = new User(usernameField.getText(), passwordField.getText());
            if(!usersManager.isUsernameTaken(u.getUsername())) {
                System.out.println("invalid username");
                return;
            }
            if(!usersManager.isPasswordCorrect(u)) {
                System.out.println("wrong password");
                return;
            }
            System.out.println("successfully logged in");
            clearTextFields();
        }
    }

    private class SignUpButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = new User(usernameField.getText(), passwordField.getText());
            if(usersManager.isUsernameTaken(u.getUsername())) {
                System.out.println("username taken");
                return;
            }
            usersManager.addUser(u);
            usersManager.printInfo();
            usersManager.saveData();
            System.out.println("successfully signed up");
            clearTextFields();
        }
    }

}
