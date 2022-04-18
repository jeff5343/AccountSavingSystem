package com.hong.GUI;

import com.hong.User;
import com.hong.UsersDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final UsersDatabase usersDatabase;
    private final JPanel cards;
    private final CardLayout cardLayout;

    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel errorMessage;

    public LoginPanel(UsersDatabase usersDatabase, JPanel cards) {
        this.usersDatabase = usersDatabase;
        this.cards = cards;
        cardLayout = (CardLayout)cards.getLayout();

        createComponents();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        this.setPreferredSize(new Dimension(250,250));
    }

    private void createComponents() {
        this.add(new TitlePanel());
        this.add(new CenterPanel());
        this.add(new BottomPanel());
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
            AccountPanel accountPanel = (AccountPanel)cards.getComponent(1);
            accountPanel.setAccount(usersDatabase.getUserFromDatabase(u));
            cardLayout.show(cards, MainFrame.ACCOUNTPANEL);
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

            System.out.println("successfully signed up");
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
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 7, 7));
            this.setPreferredSize(new Dimension(235,40));

            JButton loginButton = new CustomButton("login");
            JButton signUpButton = new CustomButton("sign up");
            loginButton.addActionListener(new LogInButtonAction());
            signUpButton.addActionListener(new SignUpButtonAction());

            this.add(loginButton);
            this.add(signUpButton);
        }

        private class CustomButton extends JButton {
            public CustomButton(String text) {
                super(text);

                this.setFont(new Font(null, Font.PLAIN, 10));
                this.setForeground(Color.lightGray);
                this.setBackground(Color.darkGray);
                this.setOpaque(true);
                this.setBorderPainted(false);
                this.setPreferredSize(new Dimension(80,25));
                this.setFocusable(false);
            }
        }
    }

}
