package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;
import java.awt.Color;

class Particle {
    final MyVector position;
    final MyVector velocity;
    long lifespan; // in milliseconds
    final Color color;

    Particle(MyVector position, MyVector velocity, long lifespan, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.lifespan = lifespan;
        this.color = color;
    }

    void update(long deltaTime) {
        position.add(MyVector.mult(velocity, (float)deltaTime / 1000f)); // scale velocity by seconds
        lifespan -= deltaTime;
    }

    boolean isAlive() {
        return lifespan > 0;
    }
}
