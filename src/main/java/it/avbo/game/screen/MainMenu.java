package it.avbo.game.screen;

import it.avbo.game.GamePanel;

import java.awt.*;

public class MainMenu {

    private final Graphics g;

    public MainMenu(Graphics graphics) {
        this.g = graphics;
    }

    public Rectangle playButton = new Rectangle(GamePanel.width / 2 + 120, 150, 100, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font font = new Font("arial", Font.BOLD, 50);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Play Game", GamePanel.width / 2, 100);

        g2d.draw(playButton);
    }

}
