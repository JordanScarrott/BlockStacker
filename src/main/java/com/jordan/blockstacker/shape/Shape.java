package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.colorThings.ColourUtils;
import com.jordan.blockstacker.core.MyVector;

import java.awt.*;
import java.util.Random;

public class Shape {

    public static final int[] L_BLOCK = new int[]{0, 1, 0, -1, 1, -1};
    public static final int[] J_BLOCK = new int[]{0, 1, 0, -1, -1, -1};
    public static final int[] I_BLOCK = new int[]{0, 1, 0, -1, 0, 2};
    public static final int[] O_BLOCK = new int[]{0, 1, 1, 1, 1, 0};
    public static final int[] T_BLOCK = new int[]{1, 0, -1, 0, 0, 1};

    public MyVector location;
    public Color colour;
    private Block[] blocksInThisShape;
    private boolean isStatic;
    private final int[] shapeType; // Added to remember the shape's structure

    public Shape(MyVector location, int[] shapeType) {
        this.location = location;
        this.shapeType = shapeType; // Store the shape type
        this.addBlockCoordinates(shapeType);
        this.isStatic = false;
        this.colour = ColourUtils.randomColour();

        for (Block b : blocksInThisShape) {
            b.setBlockColor(this.colour);
        }
    }

    public static int[] randomShapeType() {
        Random rand = new Random();
        switch (rand.nextInt(5)) {
            case 0: return L_BLOCK;
            case 1: return J_BLOCK;
            case 2: return I_BLOCK;
            case 3: return O_BLOCK;
            case 4: return T_BLOCK;
            default: return T_BLOCK; // Default case
        }
    }

    public boolean addBlockCoordinates(int... a) {
        if (a.length % 2 != 0) return false;

        blocksInThisShape = new Block[(a.length / 2) + 1];

        for (int i = 0; i < blocksInThisShape.length - 1; i++) {
            int x1 = (int) location.x + a[i * 2];
            int y1 = (int) location.y + a[i * 2 + 1];
            blocksInThisShape[i] = new Block(x1, y1);
        }
        blocksInThisShape[blocksInThisShape.length - 1] = new Block((int) location.x, (int) location.y);

        return true;
    }

    public void rotateShapeOnly() {
        for (Block b : blocksInThisShape) {
            // Translate to origin
            b.location.sub(this.location);
            // Rotate
            b.location.rotate90();
            // Translate back
            b.location.add(this.location);

            // Round the location to snap to grid
            b.location.x = Math.round(b.location.x);
            b.location.y = Math.round(b.location.y);
        }
    }

    public void move(MyVector myVector) {
        this.location.add(myVector);
        for (Block b : blocksInThisShape) {
            b.location.add(myVector);
        }
    }

    // Getters and Setters
    public Block[] getBlocksInThisShape() {
        return blocksInThisShape;
    }

    public void setBlocksInThisShape(Block[] blocks) {
        this.blocksInThisShape = new Block[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            this.blocksInThisShape[i] = new Block((int)blocks[i].location.x, (int)blocks[i].location.y);
        }
    }

    public int[] getShapeType() {
        return shapeType;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }
}
