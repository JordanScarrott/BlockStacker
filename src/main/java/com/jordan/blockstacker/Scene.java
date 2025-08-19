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

    private int score = 0;

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
        for (int i = 0; i < activeShapes.size(); i++) {
            Shape shape = activeShapes.get(i);
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
                for(int j = 0 ; j < blocksPerDim; j++) {
                    if (rowFilled(j)) {
                        for(int k = 0; k < blocksPerDim; k++) {
                            allBlocks[k][j] = null;
                        }
                        score += 10;
                        System.out.println("Row " + j + " cleared!");
                        // Move Blocks down
                        moveBlocksDown(j);
                        // Check if moving Blocks down causes another row Clear
                        j--;
                    }
                }
            }
        }
    }

    public void moveActiveShapes(MyVector movement) {
        for (int i = 0; i < activeShapes.size(); i++) {
            Shape shape = activeShapes.get(i);
            if (shapeCanMove(shape, movement)) {
                shape.move(movement);
            }
        }
    }

    /**
     * Rotates all the active shapes.
     * */
    public void rotateActiveShapes() {
        for (Shape s : activeShapes) {
            rotateShape(s);
        }
    }

    /**
     * Rotates a shape, but only if the rotation is valid (within bounds and no collisions).
     * @param shape The shape to rotate.
     */
    private void rotateShape(Shape shape) {
        // Get the locations of the blocks as if they were rotated
        Block[] blocks = shape.getBlocksInThisShape();
        MyVector[] futureLocations = new MyVector[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            MyVector futureLocation = blocks[i].location.copy();
            futureLocation.sub(shape.location);
            futureLocation.rotate90();
            futureLocation.add(shape.location);
            futureLocations[i] = futureLocation;
        }

        // Check if the future locations are valid
        for (MyVector potentialLocation : futureLocations) {
            MyVector roundedLocation = new MyVector(Math.round(potentialLocation.x), Math.round(potentialLocation.y));
            if (roundedLocation.x >= blocksPerDim || roundedLocation.y >= blocksPerDim ||
                roundedLocation.x < 0 || roundedLocation.y < 0) {
                return; // Invalid rotation, so do nothing
            }
            if (allBlocks[(int) roundedLocation.x][(int) roundedLocation.y] != null) {
                return; // Collision with existing block, so do nothing
            }
        }

        // If we get here, the rotation is valid. Apply it.
        for (int i = 0; i < blocks.length; i++) {
            MyVector potentialLocation = futureLocations[i];
            MyVector roundedLocation = new MyVector(Math.round(potentialLocation.x), Math.round(potentialLocation.y));
            blocks[i].location.set(roundedLocation);
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

    public int getScore() {
        return score;
    }
}
