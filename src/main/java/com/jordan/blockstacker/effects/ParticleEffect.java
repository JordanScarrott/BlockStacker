package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleEffect {

    private List<Particle> particles;
    private static final Random rand = new Random();

    public ParticleEffect(MyVector blockPosition, Color blockColor) {
        this.particles = new ArrayList<>();
        int numberOfParticles = 10 + rand.nextInt(5); // 10 to 14 particles

        for (int i = 0; i < numberOfParticles; i++) {
            float velX = (rand.nextFloat() - 0.5f) * 0.1f; // small random velocity
            float velY = (rand.nextFloat() - 0.5f) * 0.1f;
            MyVector velocity = new MyVector(velX, velY);
            int lifespan = 30 + rand.nextInt(30); // lifespan of 30-59 ticks
            particles.add(new Particle(blockPosition, velocity, blockColor, lifespan));
        }
    }

    public void update() {
        particles.removeIf(p -> {
            p.update();
            return !p.isAlive();
        });
    }

    public boolean isFinished() {
        return particles.isEmpty();
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
