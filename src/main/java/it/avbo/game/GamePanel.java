package it.avbo.game;

import it.avbo.game.screen.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    public boolean running = false;
    private Thread thread;
    public MainMenu mainMenu;
    private Graphics graphics;
    private BufferedImage img;

    public GamePanel(int width, int height) {
        this.width = width;
        this.height = height;
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


        while (running) {
            update();
            render();
            draw();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = img.getGraphics();
    }


    public void update() {

    }

    public void render() {
        if (graphics == null) return;

        graphics.setColor(new Color(66, 134, 244));
        graphics.fillRect(0, 0, width, height);
    }

    public void draw() {
        Graphics graphics2 = this.getGraphics();
        graphics2.drawImage(img, 0, 0, width, height, null);
        graphics2.dispose();

    }

}
