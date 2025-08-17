package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.colorThings.ColourUtils;
import com.jordan.blockstacker.core.MyVector;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jordan on 2016-11-29.
 */
public class Shape {

    public static int[] L_BLOCK = new int[]{0, 1, 0, -1, 1, -1};
    public static int[] J_BLOCK = new int[]{0, 1, 0, -1, -1, -1};
    public static int[] I_BLOCK = new int[]{0, 1, 0, -1, 0, 2};
    public static int[] O_BLOCK = new int[]{0, 1, 1, 1, 1, 0};
    public static int[] T_BLOCK = new int[]{1, 0, -1, 0, 0, 1};

    public MyVector location;
    public Color colour;
    private Block[] blocksInThisShape;
    private boolean isStatic;

    // Constructor
    public Shape(MyVector location, int[] shapeType) {
        this.location = location;
        this.addBlockCoordinates(shapeType);
        this.isStatic = false;
        this.colour = ColourUtils.randomColour();

        // Set the colour of each block in this shape
        for (Block b : blocksInThisShape) {
            b.setBlockColor(this.colour);
        }
    }

    /**
     * Rotates all the blocks in this shape 90deg about this
     * Shapes location Vector
     * */
    public void rotateShape90() {
        for (Block b : blocksInThisShape) {
            b.location.sub(this.location);
            b.location.rotate90();
            b.location.add(this.location);
        }
    }

    /**
     * @return a random ShapeType
     * */
    public static int[] randomShapeType() {
        int[] shapeType = null;
        Random rand = new Random();

        switch (rand.nextInt(5)) {
            case 0:
                shapeType = L_BLOCK;
                break;
            case 1:
                shapeType =  J_BLOCK;
                break;
            case 2:
                shapeType = I_BLOCK;
                break;
            case 3:
                shapeType = O_BLOCK;
                break;
            case 4:
                shapeType = T_BLOCK;
                break;
            default:
        }

        return shapeType;
    }

    /**
     *
     * */
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

    /**
     *
     * */
    public void move(int x, int y) {
        move(new MyVector(x, y));
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

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

}
