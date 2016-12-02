import core.MyVector;
import shape.Block;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jordan on 2016-11-20.
 */
public class Display extends JFrame {

    private int x, y;
    /**
     * The number of rows and columns in the grid
     * ie: number of blocks per width and height
     */
    private int blocksPerDim = 15;
    private int blockWidth, blockHeight;

    private Scene scene;

    public Display(int x, int y) {
        super("Tetris");
        this.x = x;
        this.y = y;

        blockWidth = x / blocksPerDim;
        blockHeight = y / blocksPerDim;

        scene = new Scene(x, y, blocksPerDim);

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

        this.addKeyListener(new KeyListener() {
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

    @Override
    public void paint(Graphics g) {
        // Draw the Background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, x, y);

        for (shape.Shape s : scene.getActiveShapes()) {
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
            g.drawLine(i * blockWidth, 0, i * blockWidth, y);
        }
        for (int i = 0; i < blocksPerDim; i++) {
            g.drawLine(0, i * blockHeight, x, i * blockHeight);
        }
    }
}
