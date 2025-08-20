package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;
import java.awt.Color;

public class Particle {
    public MyVector position;
    public MyVector velocity;
    public long lifespan; // in milliseconds
    public Color color;

    public Particle(MyVector position, MyVector velocity, long lifespan, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.lifespan = lifespan;
        this.color = color;
    }

    public void update(long deltaTime) {
        position.add(MyVector.mult(velocity, (float)deltaTime / 1000f)); // scale velocity by seconds
        lifespan -= deltaTime;
    }

    public boolean isAlive() {
        return lifespan > 0;
    }
}
