import core.MyVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyVectorTest {

    @Test
    @DisplayName("Test the add method")
    void testAdd() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.add(v1, v2);
        assertEquals(4, result.x, "The x component should be 4");
        assertEquals(6, result.y, "The y component should be 6");
    }

    @Test
    @DisplayName("Test the sub method")
    void testSub() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        MyVector result = MyVector.sub(v1, v2);
        assertEquals(-2, result.x, "The x component should be -2");
        assertEquals(-2, result.y, "The y component should be -2");
    }

    @Test
    @DisplayName("Test the dotProduct method")
    void testDotProduct() {
        MyVector v1 = new MyVector(1, 2);
        MyVector v2 = new MyVector(3, 4);
        float result = MyVector.dotProduct(v1, v2);
        assertEquals(11, result, "The dot product should be 11");
    }

    @Test
    @DisplayName("Test the rotate90 method")
    void testRotate90() {
        MyVector v1 = new MyVector(1, 2);
        MyVector result = MyVector.rotate90(v1);
        assertEquals(-2, result.x, "The x component should be -2");
        assertEquals(1, result.y, "The y component should be 1");
    }

    @Test
    @DisplayName("Test the magnitude method")
    void testMagnitude() {
        MyVector v1 = new MyVector(3, 4);
        float result = v1.magnitude();
        assertEquals(5, result, "The magnitude should be 5");
    }
}
