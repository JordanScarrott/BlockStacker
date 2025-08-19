package com.jordan.blockstacker;

public class GameLoop implements Runnable {

    private final Scene scene;
    private final Display display;
    private volatile boolean running = true;
    private final Thread gameThread;

    public GameLoop(Scene scene, Display display) {
        this.scene = scene;
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
        while (running) {
            try {
                Thread.sleep(GameConstants.GAME_SPEED_MS);
            } catch (InterruptedException e) {
                if (!running) {
                    // Exit loop if stop() was called
                    break;
                }
                e.printStackTrace();
                // Preserve the interrupted status
                Thread.currentThread().interrupt();
            }
            scene.step();
            display.repaint();
        }
    }
}
