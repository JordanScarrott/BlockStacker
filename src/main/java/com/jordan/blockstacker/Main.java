package com.jordan.blockstacker;

import com.jordan.blockstacker.effects.ParticleManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel mainPanel;
    private final MainMenu mainMenu;
    private final Display display;
    private final Scene scene;
    private final GameLoop gameLoop;
    private final GameInputHandler inputHandler;
    private final GameTicker gameTicker;
    private final ParticleManager particleManager;

    public Main() {
        super("BlockStacker");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create the game components
        particleManager = new ParticleManager();
        scene = new Scene(particleManager);
        display = new Display(scene, particleManager);
        inputHandler = new GameInputHandler(scene, display);
        gameTicker = new GameTicker();
        gameTicker.register(scene);
        gameTicker.register(particleManager);
        gameLoop = new GameLoop(gameTicker, display);


        // Add the key listener to the display panel
        display.addKeyListener(inputHandler);

        // Create the start game listener
        ActionListener startListener = e -> {
            cardLayout.show(mainPanel, "game");
            display.requestFocusInWindow();
            gameLoop.start();
        };

        mainMenu = new MainMenu(startListener);

        mainPanel.add(mainMenu, "menu");
        mainPanel.add(display, "game");

        add(mainPanel);

        cardLayout.show(mainPanel, "menu");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            // Set size based on constants to ensure consistency
            int frameWidth = GameConstants.GRID_DIMENSION * 30 + 15; // block size guess + padding
            int frameHeight = GameConstants.GRID_DIMENSION * 30 + 40; // block size guess + padding
            main.setSize(frameWidth, frameHeight);
            main.setResizable(false);
            main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            main.setLocationRelativeTo(null);
            main.setVisible(true);
        });
    }
}
