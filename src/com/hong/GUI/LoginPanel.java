package com.hong.GUI;

import com.hong.User;
import com.hong.UsersManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final UsersManager usersManager;

    private JTextField usernameField;
    private JTextField passwordField;

    public LoginPanel(UsersManager usersManager) {
        this.usersManager = usersManager;
        createComponents();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(250,135));
    }

    private void createComponents() {
        JLabel titleLabel = new JLabel("Hello", SwingConstants.CENTER);
        titleLabel.setFont(new Font(null, Font.BOLD, 15));
        titleLabel.setPreferredSize(new Dimension(200,20));

        JLabel usernameLabel = new JLabel("username:", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("password:", SwingConstants.CENTER);
        usernameLabel.setPreferredSize(new Dimension(75,25));
        passwordLabel.setPreferredSize(new Dimension(75,25));

        usernameField = new JTextField();
        passwordField = new JTextField(); // change to JPasswordField soon
        usernameField.setPreferredSize(new Dimension(125,25));
        passwordField.setPreferredSize(new Dimension(125,25));

        JButton loginButton = new JButton("login");
        JButton signUpButton = new JButton("sign up");
        loginButton.addActionListener(new LogInButtonAction());
        signUpButton.addActionListener(new SignUpButtonAction());
        loginButton.setFocusable(false);
        signUpButton.setFocusable(false);

        this.add(titleLabel);
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
