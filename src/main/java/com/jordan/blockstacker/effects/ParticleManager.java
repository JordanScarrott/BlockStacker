package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.Updatable;
import com.jordan.blockstacker.core.MyVector;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleManager implements Updatable {
    private static final int UPDATE_INTERVAL_MS = 8; // ~125 FPS
    private static final int MIN_PARTICLES_PER_BURST = 25;
    private static final int EXTRA_PARTICLES_PER_BURST = 15;
    private static final float MIN_PARTICLE_SPEED = 4f;
    private static final float EXTRA_PARTICLE_SPEED = 6f;
    private static final int MIN_LIFESPAN_MS = 500;
    private static final int EXTRA_LIFESPAN_MS = 500;
    private static final int PARTICLE_SIZE = 3;


    private final List<Particle> particles = new ArrayList<>();
    private final Random rand = new Random();
    private long timeAccumulator = 0;

    public void spawnParticles(MyVector blockPosition, Color color) {
        int numberOfParticles = MIN_PARTICLES_PER_BURST + rand.nextInt(EXTRA_PARTICLES_PER_BURST);
        for (int i = 0; i < numberOfParticles; i++) {
            MyVector particlePos = new MyVector(blockPosition.x + 0.5f, blockPosition.y + 0.5f);

            float speed = MIN_PARTICLE_SPEED + rand.nextFloat() * EXTRA_PARTICLE_SPEED;
            double angle = rand.nextDouble() * 2 * Math.PI;
            MyVector velocity = new MyVector((float)(Math.cos(angle) * speed), (float)(Math.sin(angle) * speed));

            long lifespan = MIN_LIFESPAN_MS + rand.nextInt(EXTRA_LIFESPAN_MS);

            particles.add(new Particle(particlePos, velocity, lifespan, color));
        }
    }

    @Override
    public void update(long deltaTime) {
        timeAccumulator += deltaTime;
        while (timeAccumulator >= UPDATE_INTERVAL_MS) {
            for (Particle p : particles) {
                p.update(UPDATE_INTERVAL_MS);
            }
            particles.removeIf(particle -> !particle.isAlive());
            timeAccumulator -= UPDATE_INTERVAL_MS;
        }
    }

    public void render(Graphics g, int blockWidth, int blockHeight) {
        for (Particle p : particles) {
            g.setColor(p.color);
            int x = (int) (p.position.x * blockWidth);
            int y = (int) (p.position.y * blockHeight);
            g.fillRect(x, y, PARTICLE_SIZE, PARTICLE_SIZE);
        }
    }
}
