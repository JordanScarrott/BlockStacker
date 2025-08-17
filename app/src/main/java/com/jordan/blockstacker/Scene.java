package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jordan on 2016-11-29.
 */
public class Scene {

    private int x, y, blocksPerDim;
    private Block[][] allBlocks;
//    private ArrayList<Block>

    private ArrayList<Shape> activeShapes;

    private Random rand = new Random();

    public Scene(int x, int y, int blocksPerDim) {
        this.x = x;
        this.y = y;
        this.blocksPerDim = blocksPerDim;

        allBlocks = new Block[blocksPerDim][blocksPerDim];

        activeShapes = new ArrayList<>();

        Shape aShape = new Shape(new MyVector(3, 0), Shape.T_BLOCK);
        activeShapes.add(aShape);
    }

    /**
     * Step the game forward by one thing
     */
    public void step() {
        step(new MyVector(0, 1));
    }

    public void step(MyVector movement) {
        // Check if block can move in direction it wants to
        // If it can then move it
        // Else set as static
        for (Shape shape : activeShapes) {
            if (shapeCanMove(shape, movement)) {
                shape.move(movement);
            } else {
                shape.setStatic(true);
                // Copy every Block object into the allBlocks array
                for (Block b : shape.getBlocksInThisShape()) {
                    allBlocks[(int)b.location.x][(int)b.location.y] = new Block((int)b.location.x, (int)b.location.y);
                    allBlocks[(int)b.location.x][(int)b.location.y].setBlockColor(b.getBlockColor());
                }
                activeShapes.remove(shape);

                activeShapes.add(new Shape(new MyVector(rand.nextInt(blocksPerDim - 3) + 2, 0), Shape.randomShapeType()));

                // Row Clearing
                for(int i = 0 ; i < blocksPerDim; i++) {
                    if (rowFilled(i)) {
                        for(int j = 0; j < blocksPerDim; j++) {
                            allBlocks[j][i] = null;
                        }
                        System.out.println("Row " + i + " cleared!");
                        // Move Blocks down
                        moveBlocksDown(i);
                        // Check if moving Blocks down causes another row Clear
                        i--;
                    }
                }
            }
        }
    }

    /**
     * Rotates all the active shapes
     * */
    public void rotateActiveShapes() {
        for (Shape s : activeShapes) {
            s.rotateShape90();
        }
    }

    /**
     *
     * */
    public void moveBlocksDown(int startRow) {
        for (int i = startRow; i > 0; i--) {
            for(int j = 0; j < blocksPerDim; j++) {
                allBlocks[j][i] = allBlocks[j][i-1];
                if (allBlocks[j][i] != null) {
                    allBlocks[j][i].location.add(0, 1);
                }
            }
        }
    }

    /**
     * Checks to see if a row should be cleared
     * */
    public boolean rowFilled(int row) {
        for(int i = 0; i < blocksPerDim; i++) {
            if (allBlocks[i][row] == null) {
                return false;
            }
        }

        return true;
    }

    /**
     * @returns true if location of each Block in a Shape + movement is not occupied
     * by another Block on the grid
     */
    public boolean shapeCanMove(Shape shape, MyVector movement) {
        MyVector potentialMove = new MyVector();
        for (Block b : shape.getBlocksInThisShape()) {
            potentialMove = MyVector.add(b.location, movement);

            // Array Bounds Protection
            if (potentialMove.x >= blocksPerDim
                    || potentialMove.y >= blocksPerDim
                    || potentialMove.x < 0
                    || potentialMove.y < 0) {
                return false;
            }

            // Check if any Block in this shape will hit another active block
            if (allBlocks[(int) potentialMove.x][(int) potentialMove.y] != null) {
                return false;
            }
        }
        return true;
    }


    // Constructor
    public ArrayList<Shape> getActiveShapes() {
        return activeShapes;
    }

    public Block[][] getAllBlocks() {
        return allBlocks;
    }
}
