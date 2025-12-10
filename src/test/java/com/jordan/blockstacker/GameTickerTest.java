package com.jordan.blockstacker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTickerTest {

    @Test
    public void testTickReturnsTrueWhenUpdatableUpdates() {
        GameTicker ticker = new GameTicker();
        ticker.register(deltaTime -> true); // Mock Updatable returns true

        assertTrue(ticker.tick(100));
    }

    @Test
    public void testTickReturnsFalseWhenUpdatableDoesNotUpdate() {
        GameTicker ticker = new GameTicker();
        ticker.register(deltaTime -> false); // Mock Updatable returns false

        assertFalse(ticker.tick(100));
    }

    @Test
    public void testTickReturnsTrueIfAnyUpdateReturnsTrue() {
        GameTicker ticker = new GameTicker();
        ticker.register(deltaTime -> false);
        ticker.register(deltaTime -> true);
        ticker.register(deltaTime -> false);

        assertTrue(ticker.tick(100));
    }
}
