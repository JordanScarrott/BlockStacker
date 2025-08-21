package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ParticleManager Tests")
class ParticleManagerTest {

    private ParticleManager particleManager;

    @BeforeEach
    void setUp() {
        particleManager = new ParticleManager();
    }

    @Test
    @DisplayName("spawnParticles should add particles to the list")
    void testSpawnParticles() throws NoSuchFieldException, IllegalAccessException {
        MyVector position = new MyVector(5, 5);
        Color color = Color.BLUE;

        particleManager.spawnParticles(position, color);

        Field particlesField = ParticleManager.class.getDeclaredField("particles");
        particlesField.setAccessible(true);
        List<Particle> particles = (List<Particle>) particlesField.get(particleManager);

        assertFalse(particles.isEmpty(), "Particle list should not be empty after spawning.");
    }

    @Test
    @DisplayName("update should remove dead particles")
    void testUpdateRemovesDeadParticles() throws NoSuchFieldException, IllegalAccessException {
        MyVector position = new MyVector(0, 0);
        MyVector velocity = new MyVector(0, 0);
        Color color = Color.GREEN;

        // Add a particle with a very short lifespan
        Particle shortLivedParticle = new Particle(position, velocity, 10, color);

        Field particlesField = ParticleManager.class.getDeclaredField("particles");
        particlesField.setAccessible(true);
        List<Particle> particles = (List<Particle>) particlesField.get(particleManager);
        particles.add(shortLivedParticle);

        // Update manager for a duration longer than the particle's lifespan
        particleManager.update(100);

        assertTrue(particles.isEmpty(), "Dead particles should be removed after update.");
    }

    @Test
    @DisplayName("update should not remove living particles")
    void testUpdateKeepsLivingParticles() throws NoSuchFieldException, IllegalAccessException {
        MyVector position = new MyVector(0, 0);
        MyVector velocity = new MyVector(0, 0);
        Color color = Color.YELLOW;

        // Add a particle with a long lifespan
        Particle longLivedParticle = new Particle(position, velocity, 1000, color);

        Field particlesField = ParticleManager.class.getDeclaredField("particles");
        particlesField.setAccessible(true);
        List<Particle> particles = (List<Particle>) particlesField.get(particleManager);
        particles.add(longLivedParticle);

        // Update manager for a short duration
        particleManager.update(100);

        assertEquals(1, particles.size(), "Living particles should not be removed after update.");
    }
}
