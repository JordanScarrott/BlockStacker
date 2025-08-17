package com.jordan.blockstacker.shape;

import com.jordan.blockstacker.core.MyVector;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BlockTest {

    private static final float DELTA = 1e-6f;

    @Test
    public void testConstructor() {
        Block block = new Block(10, 20);
        assertEquals(10, block.location.x, DELTA);
        assertEquals(20, block.location.y, DELTA);
    }

    @Test
    public void testGetSetBlockColor() {
        Block block = new Block(0, 0);
        Color color = Color.RED;
        block.setBlockColor(color);
        assertSame(color, block.getBlockColor());
    }

    @Test
    public void testGetSetLocation() {
        Block block = new Block(0, 0);
        MyVector newLocation = new MyVector(5, 5);
        block.setLocation(newLocation);
        assertSame(newLocation, block.getLocation());
        assertEquals(5, block.getLocation().x, DELTA);
        assertEquals(5, block.getLocation().y, DELTA);
    }
}
