package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;

import java.util.Random;

public class Scene implements Updatable {

    private final Block[][] grid;
    private int score = 0;
    private long timeAccumulator = 0;
    private Shape activeShape;
    private final Random rand = new Random();

    public Scene() {
        this.grid = new Block[GameConstants.GRID_DIMENSION][GameConstants.GRID_DIMENSION];
        spawnNewShape();
    }

    public void step() {
        step(new MyVector(0, 1));
    }

    public void step(MyVector movement) {
        if (activeShape == null) {
            spawnNewShape();
            return;
        }

        if (shapeCanMove(activeShape, movement)) {
            activeShape.move(movement);
        } else {
            solidifyActiveShape();
            int clearedRows = clearCompletedRows();
            score += clearedRows * GameConstants.SCORE_PER_ROW;
            spawnNewShape();
        }
    }

    private void solidifyActiveShape() {
        for (Block b : activeShape.getBlocksInThisShape()) {
            int x = (int) b.location.x;
            int y = (int) b.location.y;
            if (isWithinBounds(x, y)) {
                grid[x][y] = new Block(x, y);
                grid[x][y].setBlockColor(b.getBlockColor());

                // Chance to become a bomb
                if (rand.nextInt(100) < GameConstants.BOMB_CHANCE_PERCENT) {
                    grid[x][y].makeBomb();
                }
            }
        }
    }

    // package-private for testing
    int clearCompletedRows() {
        int rowsCleared = 0;
        for (int j = GameConstants.GRID_DIMENSION - 1; j >= 0; j--) {
            if (isRowFilled(j)) {
                clearRow(j);
                moveBlocksDown(j);
                rowsCleared++;
                // Since rows have shifted down, we need to check the same row index again.
                j++;
            }
        }
        return rowsCleared;
    }

    private void spawnNewShape() {
        int[] shapeType = Shape.randomShapeType();
        MyVector startPos = new MyVector(GameConstants.INITIAL_SHAPE_X, GameConstants.INITIAL_SHAPE_Y);
        this.activeShape = new Shape(startPos, shapeType);
    }

    public void moveActiveShape(MyVector movement) {
        if (activeShape != null && shapeCanMove(activeShape, movement)) {
            activeShape.move(movement);
        }
    }

    public void rotateActiveShape() {
        if (activeShape == null) return;

        // Create a temporary rotated shape to check for validity
        Shape testShape = new Shape(activeShape.location.copy(), activeShape.getShapeType());
        testShape.setBlocksInThisShape(activeShape.getBlocksInThisShape());

        // Perform a test rotation
        testShape.rotateShapeOnly();

        if (isRotationValid(testShape)) {
            activeShape.rotateShapeOnly();
        }
    }

    private boolean isRotationValid(Shape shape) {
        for (Block b : shape.getBlocksInThisShape()) {
            int x = (int) Math.round(b.location.x);
            int y = (int) Math.round(b.location.y);

            if (!isWithinBounds(x, y)) {
                return false; // Out of bounds
            }
            if (grid[x][y] != null) {
                return false; // Collision with existing block
            }
        }
        return true;
    }


    private void moveBlocksDown(int clearedRow) {
        for (int i = clearedRow; i > 0; i--) {
            for (int j = 0; j < GameConstants.GRID_DIMENSION; j++) {
                grid[j][i] = grid[j][i - 1];
                if (grid[j][i] != null) {
                    grid[j][i].location.add(0, 1);
                }
            }
        }
    }

    private void clearRow(int row) {
        for (int i = 0; i < GameConstants.GRID_DIMENSION; i++) {
            grid[i][row] = null;
        }
        System.out.println("Row " + row + " cleared!");
    }


    private boolean isRowFilled(int row) {
        for (int i = 0; i < GameConstants.GRID_DIMENSION; i++) {
            if (grid[i][row] == null) {
                return false;
            }
        }
        return true;
    }

    public boolean shapeCanMove(Shape shape, MyVector movement) {
        for (Block b : shape.getBlocksInThisShape()) {
            MyVector nextPos = MyVector.add(b.location, movement);
            int x = (int) nextPos.x;
            int y = (int) nextPos.y;

            if (!isWithinBounds(x,y)) {
                return false;
            }
            if (grid[x][y] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < GameConstants.GRID_DIMENSION && y >= 0 && y < GameConstants.GRID_DIMENSION;
    }

    // Getters for the Display class
    public Shape getActiveShape() {
        return activeShape;
    }

    // Setter for testing
    public void setActiveShape(Shape shape) {
        this.activeShape = shape;
    }

    public Block[][] getGrid() {
        return grid;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void update(long deltaTime) {
        timeAccumulator += deltaTime;

        if (timeAccumulator >= GameConstants.GAME_SPEED_MS) {
            step();
            timeAccumulator -= GameConstants.GAME_SPEED_MS;
        }

        handleBombs();
    }

    private void handleBombs() {
        for (int i = 0; i < GameConstants.GRID_DIMENSION; i++) {
            for (int j = 0; j < GameConstants.GRID_DIMENSION; j++) {
                Block block = grid[i][j];
                if (block != null && block.isBomb()) {
                    if (System.currentTimeMillis() - block.getBombActivationTime() > Block.BOMB_COUNTDOWN_MS) {
                        explodeBomb(i, j);
                    }
                }
            }
        }
    }

    private void explodeBomb(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;

                if (isWithinBounds(newX, newY)) {
                    grid[newX][newY] = null;
                }
            }
        }
    }
}
