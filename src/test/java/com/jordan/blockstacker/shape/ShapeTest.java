package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.core.MyVector;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeTest {

    private static final float DELTA = 1e-6f;

    @Test
    public void testConstructor() {
        MyVector initialLocation = new MyVector(5, 5);
        Shape shape = new Shape(initialLocation, Shape.L_BLOCK);

        assertSame(initialLocation, shape.location);
        assertNotNull(shape.colour);
        assertFalse(shape.isStatic());

        // L_BLOCK has 3 relative coordinates, so 3 + 1 = 4 blocks total
        assertEquals(4, shape.getBlocksInThisShape().length);

        // Check block locations
        // Block 0: 5+0, 5+1 -> (5, 6)
        // Block 1: 5+0, 5-1 -> (5, 4)
        // Block 2: 5+1, 5-1 -> (6, 4)
        // Block 3: 5, 5 (center)
        Block[] blocks = shape.getBlocksInThisShape();
        assertEquals(5, blocks[0].location.x, DELTA);
        assertEquals(6, blocks[0].location.y, DELTA);

        assertEquals(5, blocks[1].location.x, DELTA);
        assertEquals(4, blocks[1].location.y, DELTA);

        assertEquals(6, blocks[2].location.x, DELTA);
        assertEquals(4, blocks[2].location.y, DELTA);

        assertEquals(5, blocks[3].location.x, DELTA);
        assertEquals(5, blocks[3].location.y, DELTA);

        // Check that all blocks have the same color as the shape
        for (Block b : blocks) {
            assertSame(shape.colour, b.getBlockColor());
        }
    }


    @Test
    public void testRandomShapeType() {
        List<int[]> expectedShapes = Arrays.asList(
                Shape.L_BLOCK,
                Shape.J_BLOCK,
                Shape.I_BLOCK,
                Shape.O_BLOCK,
                Shape.T_BLOCK
        );

        for (int i = 0; i < 100; i++) {
            int[] randomShape = Shape.randomShapeType();
            assertTrue(expectedShapes.stream().anyMatch(shape -> Arrays.equals(shape, randomShape)));
        }
    }

    @Test
    public void testAddBlockCoordinates() {
        Shape shape = new Shape(new MyVector(0, 0), new int[]{});

        // Test with even length array
        assertTrue(shape.addBlockCoordinates(1, 2, 3, 4));
        assertEquals(3, shape.getBlocksInThisShape().length); // 2 pairs + center

        // Test with odd length array
        assertFalse(shape.addBlockCoordinates(1, 2, 3));
    }

    @Test
    public void testMove() {
        MyVector initialLocation = new MyVector(5, 5);
        Shape shape = new Shape(initialLocation, Shape.L_BLOCK);

        // Store initial locations
        Block[] blocks = shape.getBlocksInThisShape();
        MyVector[] initialBlockLocations = new MyVector[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            initialBlockLocations[i] = blocks[i].location.copy();
        }

        MyVector moveVector = new MyVector(2, -3);
        shape.move(moveVector);

        // Check shape location
        assertEquals(7, shape.location.x, DELTA);
        assertEquals(2, shape.location.y, DELTA);

        // Check block locations
        Block[] movedBlocks = shape.getBlocksInThisShape();
        for (int i = 0; i < movedBlocks.length; i++) {
            assertEquals(initialBlockLocations[i].x + moveVector.x, movedBlocks[i].location.x, DELTA);
            assertEquals(initialBlockLocations[i].y + moveVector.y, movedBlocks[i].location.y, DELTA);
        }
    }

    @Test
    public void testGetSetStatic() {
        Shape shape = new Shape(new MyVector(0, 0), Shape.L_BLOCK);
        assertFalse(shape.isStatic());
        shape.setStatic(true);
        assertTrue(shape.isStatic());
    }
}
