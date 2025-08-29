package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.core.MyVector;

import java.awt.*;

/**
 * Created by Jordan on 2016-11-29.
 */
public class Block {

    public MyVector location;
    private Color blockColor;
    private boolean isBomb = false;
    private long bombActivationTime = 0;
    public static final long BOMB_COUNTDOWN_MS = 3000; // 3 seconds

    // Constructor
    public Block(int x, int y) {
        this.location = new MyVector(x, y);
    }


    // Getters and Setters
    public Color getBlockColor() {
        if (isBomb) {
            // Flashing effect for the bomb
            if ((System.currentTimeMillis() / 200) % 2 == 0) {
                return Color.RED;
            } else {
                return Color.YELLOW;
            }
        }
        return blockColor;
    }

    public void setBlockColor(Color blockColor) {
        this.blockColor = blockColor;
    }

    public MyVector getLocation() {
        return location;
    }

    public void setLocation(MyVector location) {
        this.location = location;
    }

    public void makeBomb() {
        this.isBomb = true;
        this.bombActivationTime = System.currentTimeMillis();
    }

    public boolean isBomb() {
        return isBomb;
    }

    public long getBombActivationTime() {
        return bombActivationTime;
    }
}
