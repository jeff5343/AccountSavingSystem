package com.hong.GUI;

import javax.swing.*;
import java.awt.*;

public class DarkButton extends JButton {
    public DarkButton(String text) {
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
