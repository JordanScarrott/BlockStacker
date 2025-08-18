package com.jordan.blockstacker.effects;

import com.jordan.blockstacker.core.MyVector;

import java.awt.Color;

public class Particle {

    private MyVector position;
    private MyVector velocity;
    private Color color;
    private int lifespan;

    public Particle(MyVector position, MyVector velocity, Color color, int lifespan) {
        this.position = new MyVector(position.x, position.y);
        this.velocity = velocity;
        this.color = color;
        this.lifespan = lifespan;
    }

    public void update() {
        position.add(velocity);
        lifespan--;
    }

    public boolean isAlive() {
        return lifespan > 0;
    }

    public MyVector getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}
