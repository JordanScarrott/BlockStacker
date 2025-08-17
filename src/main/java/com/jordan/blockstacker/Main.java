package com.jordan.blockstacker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private MainMenu mainMenu;
    private Display display;

    public Main() {
        super("BlockStacker");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Start Game Listener
        ActionListener startListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "game");
                display.requestFocusInWindow();
                display.startGame();
            }
        };

        mainMenu = new MainMenu(startListener);
        display = new Display(650, 650);

        mainPanel.add(mainMenu, "menu");
        mainPanel.add(display, "game");

        add(mainPanel);

        cardLayout.show(mainPanel, "menu");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setSize(650, 650);
            main.setResizable(false);
            main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
