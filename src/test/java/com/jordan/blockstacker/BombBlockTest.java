package com.jordan.blockstacker;

import com.jordan.blockstacker.shape.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BombBlockTest {

    @Test
    public void testBombBlockCreation() {
        Block block = new Block(0, 0);
        assertFalse(block.isBomb());
        block.makeBomb();
        assertTrue(block.isBomb());
    }

    @Test
    public void testBombBlockExplosion() throws InterruptedException {
        Scene scene = new Scene();
        Block[][] grid = scene.getGrid();

        // Place a bomb block and some surrounding blocks
        Block bombBlock = new Block(5, 5);
        bombBlock.makeBomb();
        grid[5][5] = bombBlock;

        grid[4][4] = new Block(4, 4);
        grid[5][4] = new Block(5, 4);
        grid[6][4] = new Block(6, 4);
        grid[4][5] = new Block(4, 5);
        grid[6][5] = new Block(6, 5);
        grid[4][6] = new Block(4, 6);
        grid[5][6] = new Block(5, 6);
        grid[6][6] = new Block(6, 6);

        // Wait for the bomb to explode
        Thread.sleep(Block.BOMB_COUNTDOWN_MS + 100);

        // Update the scene to trigger the explosion
        scene.update(0);

        // Check that the bomb and surrounding blocks are cleared
        for (int i = 4; i <= 6; i++) {
            for (int j = 4; j <= 6; j++) {
                assertNull(grid[i][j]);
            }
        }
    }
}
