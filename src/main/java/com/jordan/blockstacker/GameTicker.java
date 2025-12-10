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

    public boolean tick(long deltaTime) {
        boolean anyUpdated = false;
        for (Updatable updatable : updatables) {
            if (updatable.update(deltaTime)) {
                anyUpdated = true;
            }
        }
        return anyUpdated;
    }
}
