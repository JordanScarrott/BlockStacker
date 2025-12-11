package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameInputHandler implements KeyListener {

    private final Scene scene;
    private final Display display;

    public GameInputHandler(Scene scene, Display display) {
        this.scene = scene;
        this.display = display;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        boolean needsRepaint = false;

        if (keyChar == GameConstants.MOVE_LEFT_KEY) {
            scene.moveActiveShape(GameConstants.VECTOR_LEFT);
            needsRepaint = true;
        } else if (keyChar == GameConstants.MOVE_RIGHT_KEY) {
            scene.moveActiveShape(GameConstants.VECTOR_RIGHT);
            needsRepaint = true;
        } else if (keyChar == GameConstants.MOVE_DOWN_KEY) {
            scene.step(GameConstants.VECTOR_DOWN);
            needsRepaint = true;
        } else if (keyChar == GameConstants.ROTATE_KEY) {
            scene.rotateActiveShape();
            needsRepaint = true;
        }

        if (needsRepaint) {
            display.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
