package com.hong.GUI;

import com.hong.User;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    private User account;
    private JLabel usernameLabel;

    public AccountPanel(User account) {
        super(new BorderLayout());

        this.account = account;
        createComponents();
        this.setPreferredSize(new Dimension(250, 250));
    }

    private void createComponents() {
        this.add(new TopPanel(), BorderLayout.PAGE_START);
        this.add(new CenterPanel(), BorderLayout.CENTER);
    }

    public void setAccount(User account) {
        this.account = account;
        usernameLabel.setText(account.getUsername() + "  ");
    }

    private class TopPanel extends JPanel {
        public TopPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.lightGray);
            this.setPreferredSize(new Dimension(100,30));

            usernameLabel = new JLabel(" ", SwingConstants.RIGHT);
            usernameLabel.setFont(new Font(null, Font.PLAIN, 10));
            usernameLabel.setForeground(Color.white);

            this.add(usernameLabel);
        }
    }

    private class CenterPanel extends JPanel {
        public CenterPanel() {
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(100,100));

            JLabel stuff = new JLabel("hehe", SwingConstants.CENTER);

            this.add(stuff);
        }
    }

}
