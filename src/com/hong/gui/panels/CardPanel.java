package com.hong.gui.panels;

import com.hong.User;
import com.hong.UsersDatabase;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private static final CardPanel instance = new CardPanel();
    public static CardPanel getInstance() {
        return instance;
    }
    private final UsersDatabase usersDatabase;
    private final CardLayout cardLayout;

    private CardPanel() {
        this.usersDatabase = UsersDatabase.getInstance();

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(new LoginPanel(), PanelOption.LOGIN.value);
        this.add(new AccountPanel(null), PanelOption.ACCOUNT.value);
    }

    public void switchPanel(PanelOption panelOption) {
        cardLayout.show(this, panelOption.value);
    }

    public void setAccountPanelInfo(User u) {
        AccountPanel accountPanel = (AccountPanel) this.getComponent(1);
        accountPanel.setAccount(usersDatabase.getUserFromDatabase(u));
    }
}
