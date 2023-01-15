package it.avbo.game.managers.map.blocks;

import it.avbo.game.util.math.AABB;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjBlock extends Block {
    public ObjBlock(BufferedImage image, Vector2f position, int w, int h) {
        super(image, position, w, h);
    }

    @Override
    public boolean update(AABB aabb) {
        return true;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect((int) position.getWorldVar().x, (int) position.getWorldVar().y, w, h);
    }
}
