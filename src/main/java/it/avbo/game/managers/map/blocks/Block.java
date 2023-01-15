package it.avbo.game.managers.map.blocks;

import it.avbo.game.util.math.AABB;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Block {

    protected int w;
    protected int h;

    protected BufferedImage image;
    protected Vector2f position;

    public Block(BufferedImage image, Vector2f position, int w, int h){
        this.image = image;
        this.position = position;
        this.w = w;
        this.h = h;
    }

    public abstract boolean update(AABB aabb);

    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(image, (int) position.getWorldVar().x, (int) position.getWorldVar().y, w, h, null);
    }

}
