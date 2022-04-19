package com.hong.GUI;

import com.hong.User;
import com.hong.UsersDatabase;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {

    private final UsersDatabase usersDatabase;
    private User account;
    private JLabel usernameLabel;
    private JLabel moneyLabel;

    public AccountPanel(UsersDatabase usersDatabase, User account) {
        super(new BorderLayout());
        this.usersDatabase = usersDatabase;
        this.account = account;

        createComponents();
        this.setPreferredSize(new Dimension(250, 250));
    }

    private void createComponents() {
        this.add(new TopPanel(), BorderLayout.PAGE_START);
        this.add(new CenterPanel(), BorderLayout.CENTER);
        this.add(new BottomPanel(), BorderLayout.PAGE_END);
    }

    public void setAccount(User account) {
        this.account = account;
        updateLabels();
    }

    private void updateLabels() {
        usernameLabel.setText(account.getUsername() + "  ");
        moneyLabel.setText(String.valueOf(account.getMoney()));
    }

    private class TopPanel extends JPanel {
        public TopPanel() {
            this.setLayout(new BorderLayout());
            this.setBackground(Color.lightGray);
            this.setPreferredSize(new Dimension(100,50));

            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/face.png"));

            usernameLabel = new JLabel(" ", SwingConstants.RIGHT);
            usernameLabel.setFont(new Font(null, Font.PLAIN, 10));
            usernameLabel.setForeground(Color.darkGray);

            moneyLabel = new JLabel(" ", SwingConstants.LEFT);
            moneyLabel.setIcon(imageIcon);
            moneyLabel.setHorizontalAlignment(JLabel.RIGHT);
            moneyLabel.setIconTextGap(2);
            moneyLabel.setFont(new Font(null, Font.PLAIN, 10));
            moneyLabel.setForeground(Color.darkGray);

            this.add(usernameLabel, BorderLayout.LINE_END);
            this.add(moneyLabel, BorderLayout.LINE_START);
        }
    }

    private class CenterPanel extends JPanel {
        public CenterPanel() {
            this.setLayout(new FlowLayout());

            JButton moneyButton = new DarkButton("+1 money");
            moneyButton.setPreferredSize(new Dimension(140, 25));
            moneyButton.addActionListener(e -> {
                account.setMoney(account.getMoney()+1);
                updateLabels();
            });
            JButton saveButton = new DarkButton("save");
            saveButton.setPreferredSize(new Dimension(140, 25));
            saveButton.addActionListener(e -> usersDatabase.saveData());

            JLabel space = new JLabel(" ");
            space.setPreferredSize(new Dimension(140,20));

            this.add(space);
            this.add(moneyButton);
            this.add(saveButton);
        }
    }

    private class BottomPanel extends JPanel {
        public BottomPanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 14));
            this.setPreferredSize(new Dimension(100,50));
            this.setBackground(Color.lightGray);

            JButton button = new DarkButton("delete account");
            button.setPreferredSize(new Dimension(140, 25));
            button.addActionListener(e -> {
                int input = JOptionPane.showConfirmDialog(
                        null,"Are you sure?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION
                );
                if(input==0) {
                    usersDatabase.removeUserFromDatabase(account);
                    usersDatabase.saveData();
                    CardPanel.cards.switchPanel(PanelOption.LOGIN);
                }
            });

            this.add(button);
        }
    }

}
