package com.jordan.blockstacker.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class MyVectorTest {

    private static final float DELTA = 1e-6f;

    @Test
    public void testConstructors() {
        MyVector v1 = new MyVector();
        assertEquals(0, v1.x, DELTA);
        assertEquals(0, v1.y, DELTA);

        MyVector v2 = new MyVector(1, 2);
        assertEquals(1, v2.x, DELTA);
        assertEquals(2, v2.y, DELTA);
    }

    @Test
    public void testStaticAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.add(v1, v2);
        assertEquals(4, result.x, DELTA);
        assertEquals(6, result.y, DELTA);
    }

    @Test
    public void testStaticSub() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.sub(v1, v2);
        assertEquals(-2, result.x, DELTA);
        assertEquals(-2, result.y, DELTA);
    }

    @Test
    public void testStaticCopy() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = MyVector.copy(v1);
        assertNotSame(v1, v2);
        assertEquals(v1.x, v2.x, DELTA);
        assertEquals(v1.y, v2.y, DELTA);
    }

    @Test
    public void testStaticMult() {
        MyVector v = new MyVector(2, 3);
        MyVector result = MyVector.mult(v, 2);
        // Note: The original implementation of static mult modifies the input vector.
        // This is generally bad practice for a static method.
        // Test will be written to match current implementation.
        assertEquals(4, result.x, DELTA);
        assertEquals(6, result.y, DELTA);
        assertEquals(4, v.x, DELTA); // a static method shouldn't do this
        assertEquals(6, v.y, DELTA); // a static method shouldn't do this
    }

    @Test
    public void testMagSq() {
        MyVector v = new MyVector(3, 4);
        assertEquals(25, MyVector.magSq(v), DELTA);
    }

    @Test
    public void testDotProduct() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        assertEquals(11, MyVector.dotProduct(v1, v2), DELTA);
    }

    @Test
    public void testAngle() {
        MyVector v1 = new MyVector(1, 0);
        MyVector v2 = new MyVector(0, 1);
        assertEquals(90, MyVector.angle(v1, v2), DELTA);

        MyVector v3 = new MyVector(1, 0);
        MyVector v4 = new MyVector(1, 0);
        assertEquals(0, MyVector.angle(v3, v4), DELTA);

        MyVector v5 = new MyVector(1, 0);
        MyVector v6 = new MyVector(-1, 0);
        assertEquals(180, MyVector.angle(v5, v6), DELTA);
    }

    @Test
    public void testDistanceSq() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(4, 6);
        assertEquals(25, MyVector.distanceSq(v1, v2), DELTA);
    }

    @Test
    public void testSet() {
        MyVector v1 = new MyVector();
        v1.set(5, 6);
        assertEquals(5, v1.x, DELTA);
        assertEquals(6, v1.y, DELTA);

        MyVector v2 = new MyVector(7, 8);
        v1.set(v2);
        assertEquals(7, v1.x, DELTA);
        assertEquals(8, v1.y, DELTA);
    }

    @Test
    public void testInstanceCopy() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = v1.copy();
        assertNotSame(v1, v2);
        assertEquals(v1.x, v2.x, DELTA);
        assertEquals(v1.y, v2.y, DELTA);
    }

    @Test
    public void testInstanceAdd() {
        MyVector v1 = new MyVector(1, 2);
        v1.add(3, 4);
        assertEquals(4, v1.x, DELTA);
        assertEquals(6, v1.y, DELTA);

        v1.add(new MyVector(1, 1));
        assertEquals(5, v1.x, DELTA);
        assertEquals(7, v1.y, DELTA);
    }

    @Test
    public void testInstanceSub() {
        MyVector v1 = new MyVector(5, 7);
        v1.sub(3, 4);
        assertEquals(2, v1.x, DELTA);
        assertEquals(3, v1.y, DELTA);

        v1.sub(new MyVector(1, 1));
        assertEquals(1, v1.x, DELTA);
        assertEquals(2, v1.y, DELTA);
    }

    @Test
    public void testInstanceMult() {
        MyVector v = new MyVector(2, 3);
        v.mult(2);
        assertEquals(4, v.x, DELTA);
        assertEquals(6, v.y, DELTA);
    }

    @Test
    public void testInstanceDiv() {
        MyVector v = new MyVector(4, 6);
        v.div(2);
        assertEquals(2, v.x, DELTA);
        assertEquals(3, v.y, DELTA);
    }

    @Test
    public void testStaticDiv() {
        MyVector v1 = new MyVector(4, 6);
        MyVector result = v1.div(v1, 2);
        assertEquals(2, result.x, DELTA);
        assertEquals(3, result.y, DELTA);
    }

    @Test
    public void testBearing() {
        MyVector v1 = new MyVector(1, 0);
        assertEquals(0, v1.bearing(), DELTA);

        MyVector v2 = new MyVector(0, 1);
        assertEquals(90, v2.bearing(), DELTA);
    }

    @Test
    public void testMagnitude() {
        MyVector v = new MyVector(3, 4);
        assertEquals(5, v.magnitude(), DELTA);
    }

    @Test
    public void testNormalize() {
        MyVector v = new MyVector(3, 4);
        MyVector normalized = v.normalize();
        assertEquals(0.6, normalized.x, DELTA);
        assertEquals(0.8, normalized.y, DELTA);
        assertEquals(1, normalized.magnitude(), DELTA);
    }

    @Test
    public void testNormalizeZeroVector() {
        MyVector v = new MyVector(0, 0);
        MyVector normalized = v.normalize();
        assertEquals(0, normalized.x, DELTA);
        assertEquals(0, normalized.y, DELTA);
    }

    @Test
    public void testInstanceDotProduct() {
        MyVector v1 = new MyVector(1, 2);
        assertEquals(11, v1.dotProduct(3, 4), DELTA);
        assertEquals(11, v1.dotProduct(new MyVector(3, 4)), DELTA);
    }

    @Test
    public void testCrossProduct() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        assertEquals(-2, v1.cross(v2), DELTA);
    }

    @Test
    public void testInstanceAngle() {
        MyVector v1 = new MyVector(1, 0);
        assertEquals(90, v1.angle(0, 1), DELTA);
        assertEquals(90, v1.angle(new MyVector(0, 1)), DELTA);
    }

    @Test
    public void testInstanceRotate90() {
        MyVector v = new MyVector(1, 2);
        v.rotate90();
        assertEquals(-2, v.x, DELTA);
        assertEquals(1, v.y, DELTA);
    }

    @Test
    public void testStaticRotate90() {
        MyVector v = new MyVector(1, 2);
        MyVector rotated = MyVector.rotate90(v);
        assertEquals(-2, rotated.x, DELTA);
        assertEquals(1, rotated.y, DELTA);
    }

    @Test
    public void testInstanceRotateMin90() {
        MyVector v = new MyVector(1, 2);
        v.rotateMin90();
        assertEquals(2, v.x, DELTA);
        assertEquals(-1, v.y, DELTA);
    }

    @Test
    public void testStaticRotateMin90() {
        MyVector v = new MyVector(1, 2);
        MyVector rotated = MyVector.rotateMin90(v);
        assertEquals(2, rotated.x, DELTA);
        assertEquals(-1, rotated.y, DELTA);
    }

    @Test
    public void testToString() {
        MyVector v = new MyVector(1.23f, 4.56f);
        assertEquals("MyVector[1.23, 4.56]", v.toString());
    }
}
