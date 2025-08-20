package com.jordan.blockstacker;

public class GameLoop implements Runnable {

    private final GameTicker gameTicker;
    private final Display display;
    private volatile boolean running = true;
    private final Thread gameThread;

    public GameLoop(GameTicker gameTicker, Display display) {
        this.gameTicker = gameTicker;
        this.display = display;
        this.gameThread = new Thread(this);
    }

    public void start() {
        if (running) {
            gameThread.start();
        }
    }

    public void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();

        while (running) {
            long now = System.nanoTime();
            long deltaTime = now - lastTime;
            lastTime = now;

            gameTicker.tick(deltaTime / 1_000_000); // Convert nanoseconds to milliseconds
            display.repaint();

            try {
                // Sleep for a short period to yield CPU time
                Thread.sleep(1);
            } catch (InterruptedException e) {
                if (!running) {
                    break;
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}
