package com.jordan.blockstacker;

import com.jordan.blockstacker.shape.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {

    @Test
    public void testRowClear() {
        // Create a scene with a 10x10 grid
        Scene scene = new Scene(500, 500, 10);
        Block[][] grid = scene.getAllBlocks();

        // Fill row 9 completely
        for (int i = 0; i < 10; i++) {
            grid[i][9] = new Block(i, 9);
        }

        // Place a single block in the row above (row 8) at column 5
        grid[5][8] = new Block(5, 8);

        // Check that row 9 is identified as filled
        assertTrue(scene.rowFilled(9), "Row 9 should be detected as filled.");

        // Simulate the game step that would clear the line.
        // In the actual game, this happens inside the step() method after a piece locks.
        // We will call the sub-methods directly to test the logic.
        if (scene.rowFilled(9)) {
            // Clear the row by setting all its blocks to null
            for (int k = 0; k < 10; k++) {
                grid[k][9] = null;
            }
            // Move the blocks above down
            scene.moveBlocksDown(9);
        }

        // Assert that the block from row 8 has moved down to row 9
        assertNotNull(grid[5][9], "Block from row 8 should have moved down to row 9.");
        assertNull(grid[5][8], "The original position of the moved block should now be empty.");
        assertEquals(9, grid[5][9].location.y, "The y-coordinate of the moved block should be updated to 9.");

        // Assert that the rest of row 9 is empty
        for (int i = 0; i < 10; i++) {
            if (i != 5) { // Skip the block that moved down
                assertNull(grid[i][9], "All other blocks in row 9 should be null after clearing.");
            }
        }
    }
}
