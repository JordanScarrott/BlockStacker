package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

public class ParticleEffectTest {

    @Test
    public void testParticleUpdate() {
        MyVector position = new MyVector(0, 0);
        MyVector velocity = new MyVector(1, 1);
        Particle particle = new Particle(position, velocity, Color.RED, 10);

        particle.update();

        assertEquals(1, particle.getPosition().x);
        assertEquals(1, particle.getPosition().y);
    }

    @Test
    public void testParticleIsAlive() {
        Particle particle = new Particle(new MyVector(0, 0), new MyVector(0, 0), Color.RED, 1);
        assertTrue(particle.isAlive());

        particle.update();
        assertFalse(particle.isAlive());
    }

    @Test
    public void testParticleEffectConstructor() {
        ParticleEffect effect = new ParticleEffect(new MyVector(0, 0), Color.BLUE);
        assertTrue(effect.getParticles().size() >= 10 && effect.getParticles().size() <= 14);
    }

    @Test
    public void testParticleEffectUpdate() {
        ParticleEffect effect = new ParticleEffect(new MyVector(0, 0), Color.GREEN);
        int initialSize = effect.getParticles().size();

        // All particles have a lifespan of at least 30, so none should be removed after 1 update
        effect.update();
        assertEquals(initialSize, effect.getParticles().size());
    }

    @Test
    public void testParticleEffectIsFinished() {
        ParticleEffect effect = new ParticleEffect(new MyVector(0, 0), Color.YELLOW);
        assertFalse(effect.isFinished());

        // Simulate enough updates for all particles to die
        for (int i = 0; i < 100; i++) {
            effect.update();
        }

        assertTrue(effect.isFinished());
    }
}
