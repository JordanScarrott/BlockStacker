package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.core.MyVector;

import java.awt.*;

/**
 * Created by Jordan on 2016-11-29.
 */
public class Block {

    public MyVector location;
    private Color blockColor;

    // Constructor
    public Block(int x, int y) {
        this.location = new MyVector(x, y);
    }


    // Getters and Setters
    public Color getBlockColor() {
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
}
