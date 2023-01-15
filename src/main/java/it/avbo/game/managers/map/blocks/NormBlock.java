package it.avbo.game.managers.map.blocks;

import it.avbo.game.util.math.AABB;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormBlock extends Block {

    public NormBlock(BufferedImage image, Vector2f position, int w, int h) {
        super(image, position, w, h);
    }

    @Override
    public boolean update(AABB aabb) {
        return false;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
    }
}
