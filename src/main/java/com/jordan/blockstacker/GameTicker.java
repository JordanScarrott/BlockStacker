package com.jordan.blockstacker;

import java.util.ArrayList;
import java.util.List;

public class GameTicker {

    private final List<Updatable> updatables = new ArrayList<>();

    public void register(Updatable updatable) {
        this.updatables.add(updatable);
    }

    public void unregister(Updatable updatable) {
        this.updatables.remove(updatable);
    }

    public void tick(long deltaTime) {
        for (Updatable updatable : updatables) {
            updatable.update(deltaTime);
        }
    }
}
