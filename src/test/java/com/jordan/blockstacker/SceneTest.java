package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;
import com.jordan.blockstacker.effects.ParticleManager;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Scene Tests")
public class SceneTest {

    private Scene scene;
    private ParticleManager particleManager;
    private final int GRID_SIZE = GameConstants.GRID_DIMENSION;

    @BeforeEach
    void setUp() {
        particleManager = new ParticleManager();
        scene = new Scene(particleManager);
    }

    @Test
    @DisplayName("should initialize with an empty grid and one active shape")
    void testSceneInitialization() {
        Block[][] grid = scene.getGrid();
        assertNotNull(grid, "The grid should not be null.");
        assertEquals(GRID_SIZE, grid.length, "The grid should have " + GRID_SIZE + " rows.");
        assertEquals(GRID_SIZE, grid[0].length, "The grid should have " + GRID_SIZE + " columns.");
        assertNotNull(scene.getActiveShape(), "Active shape should not be null.");
    }

    @Nested
    @DisplayName("Line Clearing Logic")
    class LineClearingTests {

        @Test
        @DisplayName("should clear a single full line and move blocks down")
        void testClearSingleLineAndMoveDown() {
            Block[][] grid = scene.getGrid();
            int rowToClear = GRID_SIZE - 1;
            int rowAbove = GRID_SIZE - 2;

            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][rowToClear] = new Block(i, rowToClear);
            }
            grid[5][rowAbove] = new Block(5, rowAbove);

            int clearedRows = scene.clearCompletedRows();
            assertEquals(1, clearedRows, "Should have cleared one row.");

            assertNotNull(grid[5][rowToClear], "Block from row " + rowAbove + " should have moved down.");
            assertNull(grid[5][rowAbove], "The original position should now be empty.");
        }

        @Test
        @DisplayName("should increase score when a line is cleared")
        void testScoreIncreasesOnLineClear() {
            // Place a shape that will clear a line when it locks
            Block[][] grid = scene.getGrid();
            int rowToClear = GRID_SIZE - 1;
            for (int i = 0; i < GRID_SIZE; i++) {
                if (i != 5) { // Leave one space empty for the shape to fall into
                    grid[i][rowToClear] = new Block(i, rowToClear);
                }
            }
            // Move the active shape to fall into the hole
            Shape shape = scene.getActiveShape();
            // A T-Block's lowest point is at y+1. To land in rowToClear, its center needs to be at y = rowToClear-1
            MyVector moveVector = new MyVector(5 - shape.location.x, (rowToClear - 1) - shape.location.y);
            shape.move(moveVector);

            scene.step(); // This should lock the shape and clear the line

            assertEquals(GameConstants.SCORE_PER_ROW, scene.getScore());
        }

        @Test
        @DisplayName("should clear multiple lines at once and update score")
        void testClearMultipleLines() {
            Block[][] grid = scene.getGrid();
            int row1 = GRID_SIZE - 2;
            int row2 = GRID_SIZE - 1;

            // Fill two rows, leaving a 1-block wide gap to drop a piece into
            for (int i = 0; i < GRID_SIZE; i++) {
                if (i != 5) {
                    grid[i][row1] = new Block(i, row1);
                    grid[i][row2] = new Block(i, row2);
                }
            }

            // I-block is good for this. Change the active shape to an I-block.
            Shape shape = new Shape(new MyVector(5, row1 - 3), Shape.I_BLOCK);
            scene.setActiveShape(shape); // Need to add this setter to Scene.java

            scene.step();
            scene.step();
            scene.step();
            scene.step();

            assertEquals(2 * GameConstants.SCORE_PER_ROW, scene.getScore());
        }
    }

    @Nested
    @DisplayName("Shape Movement and Collision")
    class ShapeMovementAndCollisionTests {

        private Shape activeShape;

        @BeforeEach
        void setUp() {
            activeShape = scene.getActiveShape();
        }

        @Test
        @DisplayName("should not move past the bottom wall")
        void testShapeCannotMovePastBottomWall() {
            activeShape.move(new MyVector(0, GRID_SIZE - 1 - activeShape.location.y));
            assertFalse(scene.shapeCanMove(activeShape, new MyVector(0, 1)));
        }

        @Test
        @DisplayName("should not lock the piece when a horizontal move is invalid")
        void testInvalidHorizontalMoveDoesNotLockPiece() {
            activeShape.move(new MyVector(GRID_SIZE - 2 - activeShape.location.x, 0));
            MyVector initialPos = activeShape.location.copy();
            scene.moveActiveShape(new MyVector(1, 0));
            assertEquals(initialPos.x, scene.getActiveShape().location.x);
        }
    }

    @Nested
    @DisplayName("Step Method Tests")
    class StepMethodTests {
        @Test
        @DisplayName("should lock shape at the bottom and spawn a new one")
        void testStepLocksShapeAtBottom() {
            Shape originalShape = scene.getActiveShape();
            originalShape.move(new MyVector(0, GRID_SIZE - 2 - originalShape.location.y));
            scene.step();
            assertNotEquals(originalShape, scene.getActiveShape());
        }

        @Test
        @DisplayName("should lock shape on another block and spawn a new one")
        void testStepLocksShapeOnAnotherBlock() {
            Shape originalShape = scene.getActiveShape();
            int lockX = (int) originalShape.location.x;
            int lockY = 5;
            scene.getGrid()[lockX][lockY] = new Block(lockX, lockY);
            originalShape.move(new MyVector(0, lockY - 2 - originalShape.location.y));
            scene.step();
            assertNotEquals(originalShape, scene.getActiveShape());
        }
    }
}
