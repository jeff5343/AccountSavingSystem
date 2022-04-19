package com.hong.GUI;

import com.hong.User;
import com.hong.UsersDatabase;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    public static CardPanel cards = null;
    private final UsersDatabase usersDatabase;
    private final CardLayout cardLayout;

    public CardPanel(UsersDatabase usersDatabase) {
        cards = this;
        this.usersDatabase = usersDatabase;

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(new LoginPanel(usersDatabase), PanelOption.LOGIN.value);
        this.add(new AccountPanel(usersDatabase, null), PanelOption.ACCOUNT.value);
    }

    public void switchPanel(PanelOption panelOption) {
        cardLayout.show(this, panelOption.value);
    }

    public void setAccountPanelInfo(User u) {
        AccountPanel accountPanel = (AccountPanel) this.getComponent(1);
        accountPanel.setAccount(usersDatabase.getUserFromDatabase(u));
    }
}
