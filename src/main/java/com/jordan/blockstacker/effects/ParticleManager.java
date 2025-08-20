package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.Updatable;
import com.jordan.blockstacker.core.MyVector;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ParticleManager implements Updatable {
    private final List<Particle> particles = new ArrayList<>();
    private final Random rand = new Random();
    private long timeAccumulator = 0;
    private static final int UPDATE_INTERVAL_MS = 16; // ~60 FPS for particles

    public void spawnParticles(MyVector blockPosition, Color color) {
        int numberOfParticles = 10 + rand.nextInt(10); // spawn 10 to 19 particles
        for (int i = 0; i < numberOfParticles; i++) {
            // Start particles at the center of the block
            MyVector particlePos = new MyVector(blockPosition.x + 0.5f, blockPosition.y + 0.5f);

            float speed = 2f + rand.nextFloat() * 4f; // Random speed
            double angle = rand.nextDouble() * 2 * Math.PI; // Random direction
            MyVector velocity = new MyVector((float)(Math.cos(angle) * speed), (float)(Math.sin(angle) * speed));

            long lifespan = 500 + rand.nextInt(500); // 500ms to 1000ms

            particles.add(new Particle(particlePos, velocity, lifespan, color));
        }
    }

    @Override
    public void update(long deltaTime) {
        timeAccumulator += deltaTime;
        while (timeAccumulator >= UPDATE_INTERVAL_MS) {
            // Use iterator to safely remove particles while iterating
            Iterator<Particle> iterator = particles.iterator();
            while (iterator.hasNext()) {
                Particle p = iterator.next();
                p.update(UPDATE_INTERVAL_MS);
                if (!p.isAlive()) {
                    iterator.remove();
                }
            }
            timeAccumulator -= UPDATE_INTERVAL_MS;
        }
    }

    public void render(Graphics g, int blockWidth, int blockHeight) {
        for (Particle p : particles) {
            g.setColor(p.color);
            int x = (int) (p.position.x * blockWidth);
            int y = (int) (p.position.y * blockHeight);
            g.fillRect(x, y, 3, 3); // Draw small particles
        }
    }
}
