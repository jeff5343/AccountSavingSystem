package com.hong.gui.panels;

import com.hong.User;
import com.hong.UsersDatabase;
import com.hong.gui.DarkButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final UsersDatabase usersDatabase;

    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel errorMessage;

    public LoginPanel() {
        super(new BorderLayout(0,10));
        this.usersDatabase = UsersDatabase.getInstance();

        createComponents();
        this.setPreferredSize(new Dimension(250,250));
    }

    private void createComponents() {
        this.add(new TitlePanel(),BorderLayout.PAGE_START);
        this.add(new CenterPanel(),BorderLayout.CENTER);
        this.add(new BottomPanel(),BorderLayout.PAGE_END);
    }

    private void clearTextFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private class LogInButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = new User(usernameField.getText(), passwordField.getText());
            if(!usersDatabase.isUsernameTaken(u.getUsername())) {
                errorMessage.setText("invalid username!");
                return;
            }
            if(!usersDatabase.isPasswordCorrect(u)) {
                errorMessage.setText("wrong password!");
                return;
            }
            CardPanel.getInstance().setAccountPanelInfo(u);
            CardPanel.getInstance().switchPanel(PanelOption.ACCOUNT);
            clearTextFields();
        }
    }

    private class SignUpButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User u = new User(usernameField.getText(), passwordField.getText());
            if(u.getUsername().isBlank()) {
                errorMessage.setText("invalid username!");
                return;
            }
            if(usersDatabase.isUsernameTaken(u.getUsername())) {
                errorMessage.setText("username taken!");
                return;
            }
            usersDatabase.addUser(u);
            usersDatabase.printInfo();
            usersDatabase.saveData();

            errorMessage.setText("");
            clearTextFields();
        }
    }

    private class TitlePanel extends JPanel {
        public TitlePanel() {
            this.setBackground(Color.lightGray);
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(235,50));

            ImageIcon smileIcon = new ImageIcon(getClass().getResource("/images/face.png"));
            JLabel titleLabel = new JLabel("Welcome", SwingConstants.CENTER);
            titleLabel.setIcon(smileIcon);
            titleLabel.setHorizontalTextPosition(JLabel.LEFT); // of image icon
            titleLabel.setVerticalTextPosition(JLabel.CENTER); // of image icon
            titleLabel.setFont(new Font(null, Font.BOLD, 15));

            this.add(titleLabel);
        }
    }

    private class CenterPanel extends JPanel {
        public CenterPanel() {
            this.setLayout(new FlowLayout());
            this.setPreferredSize(new Dimension(235,80));

            JLabel usernameLabel = new JLabel("username:", SwingConstants.CENTER);
            JLabel passwordLabel = new JLabel("password:", SwingConstants.CENTER);
            usernameLabel.setPreferredSize(new Dimension(75,25));
            passwordLabel.setPreferredSize(new Dimension(75,25));

            usernameField = new JTextField();
            passwordField = new JTextField(); // change to JPasswordField soon
            usernameField.setPreferredSize(new Dimension(125,25));
            passwordField.setPreferredSize(new Dimension(125,25));

            errorMessage = new JLabel("", SwingConstants.CENTER);
            errorMessage.setFont(new Font(null, Font.PLAIN, 10));
            errorMessage.setForeground(Color.red);
            errorMessage.setPreferredSize(new Dimension(200, 20));

            this.add(usernameLabel);
            this.add(usernameField);
            this.add(passwordLabel);
            this.add(passwordField);
            this.add(errorMessage);
        }
    }

    private class BottomPanel extends JPanel {
        public BottomPanel() {
            this.setBackground(Color.lightGray);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 14));
            this.setPreferredSize(new Dimension(235,50));

            JButton loginButton = new DarkButton("login");
            JButton signUpButton = new DarkButton("sign up");
            loginButton.addActionListener(new LogInButtonAction());
            signUpButton.addActionListener(new SignUpButtonAction());

            this.add(loginButton);
            this.add(signUpButton);
        }
    }

}
