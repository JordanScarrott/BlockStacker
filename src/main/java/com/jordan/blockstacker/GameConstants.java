package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;

import java.awt.event.KeyEvent;

public final class GameConstants {

    private GameConstants() {
        // Private constructor to prevent instantiation
    }

    public static final int GRID_DIMENSION = 15;
    public static final int SCORE_PER_ROW = 10;
    public static final int INITIAL_SHAPE_X = 6; // Centered for a 15-width grid
    public static final int INITIAL_SHAPE_Y = 0;

    // Game loop speed in milliseconds
    public static final int GAME_SPEED_MS = 750;

    // Keyboard controls
    public static final char MOVE_LEFT_KEY = 'a';
    public static final char MOVE_RIGHT_KEY = 'd';
    public static final char MOVE_DOWN_KEY = 's';
    public static final char ROTATE_KEY = 'q';

    // Vector constants
    public static final MyVector VECTOR_DOWN = new MyVector(0, 1);
    public static final MyVector VECTOR_LEFT = new MyVector(-1, 0);
    public static final MyVector VECTOR_RIGHT = new MyVector(1, 0);
}
