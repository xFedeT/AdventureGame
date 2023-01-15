package it.avbo.game;

import it.avbo.game.managers.GameStateManager;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;
    public static int oldFrameCount;

    private Thread thread;
    public boolean running = false;

    private BufferedImage img;
    private Graphics graphics;

    public MouseHandler mouse;
    public KeyHandler key;

    public GameStateManager gameStateManager;

    public GamePanel(int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "Game Thread");
            thread.start();
        }

    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = img.getGraphics();

        mouse = new MouseHandler(this);
        key = new KeyHandler(this);

        gameStateManager = new GameStateManager();
    }

    public void run() {
        init();

        final double GAME_HERTZ = 64.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time Before Update

        final int MUBR = 3; // Must Update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 1000;
        final double TTBR = 1000000000 / TARGET_FPS; // Total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;


        while (running) {

            double now = System.nanoTime();
            int updateCount = 0;

            while ((now - updateCount > TBU) && (updateCount < MUBR)) {
                update();
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if (now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }

                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }

                now = System.nanoTime();

            }

        }
    }

    public void update() {
        gameStateManager.update();
    }

    private void input(MouseHandler mouse, KeyHandler key) {
        gameStateManager.input(mouse, key);
    }

    public void render() {
        if (graphics == null) return;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        gameStateManager.render((Graphics2D) graphics);
    }

    public void draw() {
        Graphics graphics2 = this.getGraphics();
        graphics2.drawImage(img, 0, 0, width, height, null);
        graphics2.dispose();
    }

}
