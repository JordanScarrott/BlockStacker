package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Jordan on 2016-11-20.
 */
public class Display extends JPanel {

    private int width, height;
    /**
     * The number of rows and columns in the grid
     * ie: number of blocks per width and height
     */
    private int blocksPerDim = 15;
    private int blockWidth, blockHeight;

    private Scene scene;

    public Display(int width, int height) {
        this.width = width;
        this.height = height;

        blockWidth = width / blocksPerDim;
        blockHeight = height / blocksPerDim;

        scene = new Scene(width, height, blocksPerDim);

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'a') {
                    scene.step(new MyVector(-1, 0));
                } else if (e.getKeyChar() == 'd') {
                    scene.step(new MyVector(1, 0));
                } else if (e.getKeyChar() == 's') {
                    scene.step(new MyVector(0, 1));
                } else if (e.getKeyChar() == 'q') {
                    scene.rotateActiveShapes();
                }
                //
                if (e.getKeyChar() == 'a' || e.getKeyChar() == 'd' || e.getKeyChar() == 's' || e.getKeyChar() == 'q') {
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void startGame() {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scene.step();
                repaint();
            }
        });
        t1.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        for (Shape s : scene.getActiveShapes()) {
            for (Block b : s.getBlocksInThisShape()) {
                g.setColor(b.getBlockColor());

                int x1 = (int) b.location.x;
                int y1 = (int) b.location.y;
                g.fillRect(x1 * blockWidth, y1 * blockHeight, blockWidth, blockHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x1 * blockWidth, y1 * blockHeight, blockWidth, blockHeight);
            }
        }
        for (int i = 0; i < blocksPerDim; i++) {
            for (int j = 0; j < blocksPerDim; j++) {
                Block b = scene.getAllBlocks()[i][j];
                if (scene.getAllBlocks()[i][j] != null) {
                    g.setColor(b.getBlockColor());
                    int x1 = (int) b.location.x;
                    int y1 = (int) b.location.y;
                    g.fillRect(x1 * blockWidth, y1 * blockHeight, blockWidth, blockHeight);
//                    g.setColor(Color.BLACK);
//                    g.drawRect(x1 * blockWidth, y1 * blockHeight, blockWidth, blockHeight);
                }
            }
        }


        // Draw grid
        g.setColor(Color.gray);
        for (int i = 0; i < blocksPerDim; i++) {
            g.drawLine(i * blockWidth, 0, i * blockWidth, height);
        }
        for (int i = 0; i < blocksPerDim; i++) {
            g.drawLine(0, i * blockHeight, width, i * blockHeight);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: " + scene.getScore(), 10, 20);
    }
}
