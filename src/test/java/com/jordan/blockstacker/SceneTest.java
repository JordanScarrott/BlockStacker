package com.jordan.blockstacker;

import com.jordan.blockstacker.core.MyVector;
import com.jordan.blockstacker.shape.Block;
import com.jordan.blockstacker.shape.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Scene Tests")
public class SceneTest {

    private Scene scene;
    private final int GRID_SIZE = 10;

    @BeforeEach
    void setUp() {
        scene = new Scene(500, 500, GRID_SIZE);
    }

    @Test
    @DisplayName("should initialize with an empty grid and one active shape")
    void testSceneInitialization() {
        Block[][] grid = scene.getAllBlocks();
        assertNotNull(grid, "The grid should not be null.");
        assertEquals(GRID_SIZE, grid.length, "The grid should have " + GRID_SIZE + " rows.");
        assertEquals(GRID_SIZE, grid[0].length, "The grid should have " + GRID_SIZE + " columns.");

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                assertNull(grid[i][j], "Each cell in the grid should be initially null.");
            }
        }

        assertNotNull(scene.getActiveShapes(), "Active shapes list should not be null.");
        assertEquals(1, scene.getActiveShapes().size(), "There should be one active shape upon initialization.");
    }

    @Nested
    @DisplayName("Line Clearing Logic")
    class LineClearingTests {

        @Test
        @DisplayName("should correctly identify a full row")
        void testRowIsFull() {
            Block[][] grid = scene.getAllBlocks();
            int rowToFill = GRID_SIZE - 1;

            // Fill the row
            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][rowToFill] = new Block(i, rowToFill);
            }

            assertTrue(scene.rowFilled(rowToFill), "A completely filled row should be identified as full.");
        }

        @Test
        @DisplayName("should not identify an incomplete row as full")
        void testRowIsNotFull() {
            Block[][] grid = scene.getAllBlocks();
            int rowToTest = GRID_SIZE - 1;

            // Fill the row almost completely
            for (int i = 0; i < GRID_SIZE - 1; i++) {
                grid[i][rowToTest] = new Block(i, rowToTest);
            }

            assertFalse(scene.rowFilled(rowToTest), "A partially filled row should not be identified as full.");
        }

        @Test
        @DisplayName("should move blocks down correctly")
        void testMoveBlocksDown() {
            Block[][] grid = scene.getAllBlocks();
            int rowToClear = 9;
            int rowAbove = 8;
            int col = 5;

            // Place a block in the row above the one that will be "cleared"
            grid[col][rowAbove] = new Block(col, rowAbove);

            // Manually "clear" the row below it (by leaving it null) and then move blocks down
            scene.moveBlocksDown(rowToClear);

            // Assert that the block has moved down
            assertNotNull(grid[col][rowToClear], "Block from row " + rowAbove + " should have moved down to row " + rowToClear);
            assertNull(grid[col][rowAbove], "The original position of the moved block should now be empty.");
            assertEquals(rowToClear, grid[col][rowToClear].location.y, "The y-coordinate of the moved block should be updated.");
        }

        @Test
        @DisplayName("should clear a single full line and move blocks down")
        void testClearSingleLine() {
            Block[][] grid = scene.getAllBlocks();
            int rowToClear = 9;
            int rowAbove = 8;

            // Fill row 9 completely
            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][rowToClear] = new Block(i, rowToClear);
            }

            // Place a single block in the row above (row 8) at column 5
            grid[5][rowAbove] = new Block(5, rowAbove);

            // This is a bit tricky to test without calling step().
            // The logic for clearing is inside step().
            // I will replicate the logic from step() here for a focused test.
            if (scene.rowFilled(rowToClear)) {
                // Clear the row
                for(int k = 0; k < GRID_SIZE; k++) {
                    grid[k][rowToClear] = null;
                }
                // Move blocks down
                scene.moveBlocksDown(rowToClear);
            }

            // Assert that the block from row 8 has moved down to row 9
            assertNotNull(grid[5][9], "Block from row 8 should have moved down to row 9.");
            assertEquals(9, grid[5][9].location.y, "The y-coordinate of the moved block should be updated to 9.");
            assertNull(grid[5][8], "The original position of the moved block should now be empty.");
        }

        @Test
        @DisplayName("should increase score when a line is cleared")
        void testScoreIncreasesOnLineClear() {
            Block[][] grid = scene.getAllBlocks();
            int rowToClear = GRID_SIZE - 1;

            // Fill the row
            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][rowToClear] = new Block(i, rowToClear);
            }

            // The shape starts at (3,0). It will fall down.
            // The default T-shape's lowest part is at y=1 relative to its location.
            // It needs to step 8 times for its location to become y=8.
            // At that point, its lowest block is at y=9.
            for (int i = 0; i < 8; i++) {
                scene.step();
            }

            // The next step will attempt to move to y=9, which is blocked.
            // This will lock the piece and trigger the line clear.
            scene.step();

            assertEquals(10, scene.getScore(), "Score should be 10 after clearing one line.");
        }

        @Test
        @DisplayName("should clear multiple lines at once")
        void testClearMultipleLines() {
            Block[][] grid = scene.getAllBlocks();
            int row1 = 8;
            int row2 = 9;

            // Place a block above the lines to be cleared
            grid[5][7] = new Block(5, 7);

            // Fill two rows completely
            for (int i = 0; i < GRID_SIZE; i++) {
                grid[i][row1] = new Block(i, row1);
                grid[i][row2] = new Block(i, row2);
            }

            // Replicate the line clearing logic from step()
            for (int j = 0; j < GRID_SIZE; j++) {
                if (scene.rowFilled(j)) {
                    for (int k = 0; k < GRID_SIZE; k++) {
                        grid[k][j] = null;
                    }
                    scene.moveBlocksDown(j);
                    j--; // re-check the same row
                }
            }

            // Assert that the block from row 7 has moved down by 2 rows to row 9
            assertNotNull(grid[5][9], "Block should have moved down to row 9.");
            assertNull(grid[5][7], "Original block position should be empty.");
            assertEquals(9, grid[5][9].location.y, "Block's y-coordinate should be updated.");
        }

        @Test
        @DisplayName("should not clear an incomplete line when a piece locks")
        void testStepDoesNotClearIncompleteLine() {
            Block[][] grid = scene.getAllBlocks();
            int rowToTest = GRID_SIZE - 1;

            // Fill the bottom row almost completely, leaving a hole at the far right
            for (int i = 0; i < GRID_SIZE - 1; i++) {
                grid[i][rowToTest] = new Block(i, rowToTest);
            }

            // Drop the default T-piece to the bottom.
            for (int i = 0; i < 8; i++) {
                scene.step();
            }
            scene.step(); // This step locks the piece.

            // The piece lands around x=3, so it won't fill the hole at x=9.
            // Assert that a block far from the locked piece is still there.
            assertNotNull(grid[0][rowToTest], "Block at (0, " + rowToTest + ") should not have been cleared.");
        }
    }

    @Nested
    @DisplayName("Shape Movement and Collision")
    class ShapeMovementAndCollisionTests {

        private Shape activeShape;

        @BeforeEach
        void setUp() {
            // By default, a new scene creates a T_BLOCK at position (3,0).
            // The blocks of this shape are at relative positions (0,0), (1,0), (-1,0), (0,1).
            // So, the absolute coordinates are (3,0), (4,0), (2,0), (3,1).
            activeShape = scene.getActiveShapes().get(0);
        }

        @Test
        @DisplayName("should be able to move in an empty scene")
        void testShapeCanMoveInEmptyScene() {
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(1, 0)), "Shape should be able to move right in an empty scene.");
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(-1, 0)), "Shape should be able to move left in an empty scene.");
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(0, 1)), "Shape should be able to move down in an empty scene.");
        }

        @Test
        @DisplayName("should not move past the right wall")
        void testShapeCannotMovePastRightWall() {
            // A T-Block at (3,0) has its rightmost block at x=4.
            // The grid is size 10 (0-9). The wall is at x=10.
            // It can move right 5 times. (4+5=9). The next move is blocked.
            activeShape.move(5, 0);
            assertFalse(scene.shapeCanMove(activeShape, new MyVector(1, 0)), "Shape should not be able to move past the right wall.");
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(0, 0)), "Shape should be able to stay in place.");
        }

        @Test
        @DisplayName("should not move past the left wall")
        void testShapeCannotMovePastLeftWall() {
            // A T-Block at (3,0) has its leftmost block at x=2.
            // The wall is at x=-1. It can move left 2 times. (2-2=0). The next move is blocked.
            activeShape.move(-2, 0);
            assertFalse(scene.shapeCanMove(activeShape, new MyVector(-1, 0)), "Shape should not be able to move past the left wall.");
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(0, 0)), "Shape should be able to stay in place.");
        }

        @Test
        @DisplayName("should not move past the bottom wall")
        void testShapeCannotMovePastBottomWall() {
            // A T-Block at (3,0) has its bottommost block at y=1.
            // The grid is size 10 (0-9). The wall is at y=10.
            // It can move down 8 times. (1+8=9). The next move is blocked.
            activeShape.move(0, 8);
            assertFalse(scene.shapeCanMove(activeShape, new MyVector(0, 1)), "Shape should not be able to move past the bottom wall.");
            assertTrue(scene.shapeCanMove(activeShape, new MyVector(0, 0)), "Shape should be able to stay in place.");
        }

        @Test
        @DisplayName("should not move into an occupied cell")
        void testShapeCannotMoveIntoOccupiedCell() {
            Block[][] grid = scene.getAllBlocks();
            // Place a block directly below the T-shape's body
            grid[3][2] = new Block(3, 2);

            // The T-shape has a block at (3,1). A downward move (0,1) would cause a collision at (3,2).
            assertFalse(scene.shapeCanMove(activeShape, new MyVector(0, 1)), "Shape should not be able to move into an occupied cell.");
        }
    }

    @Nested
    @DisplayName("Step Method Tests")
    class StepMethodTests {

        private Shape activeShape;

        @BeforeEach
        void setUp() {
            activeShape = scene.getActiveShapes().get(0);
        }

        @Test
        @DisplayName("should move the active shape down by one unit")
        void testStepMovesShapeDown() {
            MyVector initialPosition = new MyVector(activeShape.location.x, activeShape.location.y);
            scene.step();
            MyVector newPosition = activeShape.location;
            assertEquals(initialPosition.x, newPosition.x, "Shape's x position should not change.");
            assertEquals(initialPosition.y + 1, newPosition.y, "Shape should move down by 1 unit.");
        }

        @Test
        @DisplayName("should lock shape at the bottom and spawn a new one")
        void testStepLocksShapeAtBottom() {
            // A T-Block at (3,0) has its bottommost block at y=1.
            // It can move down 8 times to reach y=9. The 9th step will lock it.
            for (int i = 0; i < 8; i++) {
                scene.step();
            }
            assertEquals(8, activeShape.location.y); // y started at 0, now at 8. bottom of shape is at y=9.

            // The next step should lock the shape
            scene.step();

            // After locking, the original shape is removed and a new one is added.
            assertEquals(1, scene.getActiveShapes().size(), "There should still be one active shape.");
            assertNotEquals(activeShape, scene.getActiveShapes().get(0), "A new shape should have spawned.");

            // Check that the blocks of the old shape are now part of the grid
            assertNotNull(scene.getAllBlocks()[3][8], "Locked block should be on the grid."); // center
            assertNotNull(scene.getAllBlocks()[4][8], "Locked block should be on the grid."); // right
            assertNotNull(scene.getAllBlocks()[2][8], "Locked block should be on the grid."); // left
            assertNotNull(scene.getAllBlocks()[3][9], "Locked block should be on the grid."); // bottom
        }

        @Test
        @DisplayName("should lock shape on another block and spawn a new one")
        void testStepLocksShapeOnAnotherBlock() {
            // Place a block for the shape to land on
            scene.getAllBlocks()[3][5] = new Block(3, 5);

            // A T-block's bottom is at y=1. It needs to step 3 times to collide.
            // (y=0->1, y=1->2, y=2->3). At y=3, bottom of shape is at y=4. Next step collides.
            for (int i = 0; i < 3; i++) {
                scene.step();
            }
            assertEquals(3, activeShape.location.y);

            // The next step should lock the shape
            scene.step();

            assertEquals(1, scene.getActiveShapes().size(), "There should still be one active shape.");
            assertNotEquals(activeShape, scene.getActiveShapes().get(0), "A new shape should have spawned.");

            // Check that the blocks of the old shape are now part of the grid
            assertNotNull(scene.getAllBlocks()[3][3], "Locked block should be on the grid.");
            assertNotNull(scene.getAllBlocks()[4][3], "Locked block should be on the grid.");
            assertNotNull(scene.getAllBlocks()[2][3], "Locked block should be on the grid.");
            assertNotNull(scene.getAllBlocks()[3][4], "Locked block should be on the grid.");
        }
    }

    @Nested
    @DisplayName("Shape Rotation Tests")
    class ShapeRotationTests {

        private Shape activeShape;

        @BeforeEach
        void setUp() {
            activeShape = scene.getActiveShapes().get(0);
        }

        @Test
        @DisplayName("should rotate the shape 90 degrees")
        void testRotateShape() {
            // The default T-shape starts at (3,0) with blocks at:
            // (3,0) center, (4,0), (2,0), (3,1)

            scene.rotateActiveShapes();

            // After a 90-degree rotation (x,y -> -y,x) of relative vectors:
            // (0,0)->(0,0), (1,0)->(0,1), (-1,0)->(0,-1), (0,1)->(-1,0)
            // New absolute positions should be:
            // (3,0), (3,1), (3,-1), (2,0)

            Block[] blocks = activeShape.getBlocksInThisShape();
            List<MyVector> actualPositions = new ArrayList<>();
            for (Block b : blocks) {
                actualPositions.add(b.location);
            }

            List<MyVector> expectedPositions = Arrays.asList(
                new MyVector(3, 0),
                new MyVector(3, 1),
                new MyVector(3, -1),
                new MyVector(2, 0)
            );

            assertEquals(expectedPositions.size(), actualPositions.size(), "Should have the same number of blocks after rotation.");

            for (MyVector expectedPos : expectedPositions) {
                boolean found = actualPositions.stream().anyMatch(p -> p.x == expectedPos.x && p.y == expectedPos.y);
                assertTrue(found, "Expected position " + expectedPos + " was not found after rotation.");
            }
        }

        @Test
        @DisplayName("should crash when locking a shape that was rotated out of bounds")
        void testRotationOutOfBoundsCausesCrashOnLock() {
            // Rotate the initial T-shape immediately.
            // After rotation, one of its blocks will be at y=-1, which is out of bounds.
            scene.rotateActiveShapes();

            // Place a block on the grid to prevent the shape from moving down.
            // This will force the 'lock' mechanism to trigger on the next step.
            // The rotated shape has a block at (2,0). Let's block the cell below it, at (2,1).
            scene.getAllBlocks()[2][1] = new Block(2, 1);

            // The next step will fail shapeCanMove() because of the obstacle at (2,1).
            // It will then try to lock the shape in its current out-of-bounds position.
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                scene.step();
            }, "The game should crash when trying to lock a shape with a part out of bounds.");
        }
    }

    @Nested
    @DisplayName("Game Over Condition")
    class GameOverTests {

        @Test
        @DisplayName("should eventually crash when locking out-of-bounds shapes in a game-over state")
        void testGameOverLeadsToCrash() {
            Block[][] grid = scene.getAllBlocks();
            // Block the top rows to trigger a game-over state,
            // but leave a hole to prevent line clearing, which would undo the block.
            for (int i = 0; i < GRID_SIZE - 1; i++) {
                grid[i][0] = new Block(i, 0);
                grid[i][1] = new Block(i, 1);
                grid[i][2] = new Block(i, 2);
            }

            // The first step will lock the initial piece.
            scene.step();

            // Subsequent steps will spawn new random pieces that are also blocked.
            // Some of these shapes (like the I-block) are defined with negative relative
            // coordinates, causing them to spawn partially out of bounds (y < 0).
            // When the game tries to lock these shapes, it will crash with an
            // ArrayIndexOutOfBoundsException because it tries to write to allBlocks[x][-1].
            // This test asserts that this crash occurs, documenting the bug.
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                // We loop a number of times to ensure a problematic shape is likely to spawn.
                for (int i = 0; i < 20; i++) {
                    scene.step();
                }
            }, "The game should crash when trying to lock a shape with parts out of bounds.");
        }
    }
}
