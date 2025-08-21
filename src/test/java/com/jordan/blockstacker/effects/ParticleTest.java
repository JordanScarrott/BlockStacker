package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Particle Tests")
class ParticleTest {

    @Test
    @DisplayName("should update position based on velocity and deltaTime")
    void testUpdatePosition() {
        MyVector initialPosition = new MyVector(10, 10);
        MyVector velocity = new MyVector(1, 2);
        Particle particle = new Particle(initialPosition, velocity, 1000, Color.RED);

        particle.update(500); // 0.5 seconds

        assertEquals(10.5f, particle.position.x, 0.001, "X position should update correctly.");
        assertEquals(11.0f, particle.position.y, 0.001, "Y position should update correctly.");
    }

    @Test
    @DisplayName("should decrease lifespan on update")
    void testUpdateLifespan() {
        Particle particle = new Particle(new MyVector(0, 0), new MyVector(0, 0), 1000, Color.RED);
        particle.update(200);
        assertEquals(800, particle.lifespan, "Lifespan should decrease by deltaTime.");
    }

    @Test
    @DisplayName("isAlive should return true when lifespan is positive")
    void testIsAlivePositive() {
        Particle particle = new Particle(new MyVector(0, 0), new MyVector(0, 0), 1, Color.RED);
        assertTrue(particle.isAlive(), "Particle should be alive with positive lifespan.");
    }

    @Test
    @DisplayName("isAlive should return false when lifespan is zero")
    void testIsAliveZero() {
        Particle particle = new Particle(new MyVector(0, 0), new MyVector(0, 0), 0, Color.RED);
        assertFalse(particle.isAlive(), "Particle should not be alive with zero lifespan.");
    }

    @Test
    @DisplayName("isAlive should return false when lifespan is negative")
    void testIsAliveNegative() {
        Particle particle = new Particle(new MyVector(0, 0), new MyVector(0, 0), -1, Color.RED);
        assertFalse(particle.isAlive(), "Particle should not be alive with negative lifespan.");
    }
}
