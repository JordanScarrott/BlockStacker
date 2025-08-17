package com.jordan.blockstacker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {

    private JButton startButton;
    private JButton exitButton;

    public MainMenu(ActionListener startListener) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        startButton = new JButton("Start Game");
        startButton.addActionListener(startListener);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(startButton, gbc);

        gbc.gridy = 1;
        add(exitButton, gbc);
    }
}
