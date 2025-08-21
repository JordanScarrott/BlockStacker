package com.jordan.blockstacker;

import com.jordan.blockstacker.effects.ParticleManager;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {

    private final Scene scene;
    private final ParticleManager particleManager;
    private int blockWidth;
    private int blockHeight;

    public Display(Scene scene, ParticleManager particleManager) {
        this.scene = scene;
        this.particleManager = particleManager;
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // It's better to calculate this here in case the panel is resized.
        blockWidth = getWidth() / GameConstants.GRID_DIMENSION;
        blockHeight = getHeight() / GameConstants.GRID_DIMENSION;

        drawBackground(g);
        drawAllBlocks(g);
        drawActiveShape(g);
        // Render particles on top of the blocks but under the grid lines and score
        particleManager.render(g, blockWidth, blockHeight);
        drawGrid(g);
        drawScore(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawAllBlocks(Graphics g) {
        Block[][] grid = scene.getGrid();
        for (int i = 0; i < GameConstants.GRID_DIMENSION; i++) {
            for (int j = 0; j < GameConstants.GRID_DIMENSION; j++) {
                if (grid[i][j] != null) {
                    drawBlock(g, grid[i][j]);
                }
            }
        }
    }

    private void drawActiveShape(Graphics g) {
        Shape activeShape = scene.getActiveShape();
        if (activeShape != null) {
            for (Block b : activeShape.getBlocksInThisShape()) {
                // Draw a border for active blocks to distinguish them
                drawBlock(g, b, true);
            }
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < GameConstants.GRID_DIMENSION; i++) {
            g.drawLine(i * blockWidth, 0, i * blockWidth, getHeight());
            g.drawLine(0, i * blockHeight, getWidth(), i * blockHeight);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + scene.getScore(), 10, 25);
    }

    private void drawBlock(Graphics g, Block b) {
        drawBlock(g, b, false);
    }

    private void drawBlock(Graphics g, Block b, boolean withBorder) {
        if (b == null) return;

        int x = (int) b.location.x * blockWidth;
        int y = (int) b.location.y * blockHeight;

        g.setColor(b.getBlockColor());
        g.fillRect(x, y, blockWidth, blockHeight);

        if (withBorder) {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, blockWidth - 1, blockHeight - 1);
        }
    }
}
