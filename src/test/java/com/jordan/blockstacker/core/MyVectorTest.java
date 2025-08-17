package com.jordan.blockstacker.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyVectorTest {

    @Test
    public void testAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.add(v1, v2);
        assertEquals(4, result.x);
        assertEquals(6, result.y);
    }
}
