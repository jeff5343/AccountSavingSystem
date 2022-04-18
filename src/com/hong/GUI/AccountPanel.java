package com.hong.GUI;

import com.hong.User;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    private final User account;

    public AccountPanel(User account) {
        super(new BorderLayout());

        this.account = account;
        createComponents();
        this.setPreferredSize(new Dimension(250, 250));
    }

    private void createComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.gray);
        topPanel.setPreferredSize(new Dimension(100,50));
        JLabel usernameLabel = new JLabel(account.getUsername() + "  ", SwingConstants.RIGHT);
        usernameLabel.setVerticalTextPosition(JLabel.BOTTOM);
        topPanel.add(usernameLabel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(100,100));
        JLabel stuff = new JLabel("stuff will be here", SwingConstants.CENTER);
        centerPanel.add(stuff);

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(centerPanel, BorderLayout.CENTER);
    }

}
