package it.avbo.game.entity;

import it.avbo.game.graphics.Animation;
import it.avbo.game.graphics.Sprite;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;
import it.avbo.game.util.math.AABB;
import it.avbo.game.util.math.Vector2f;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private final int UP = 3;
    private final int DOWN = 2;
    private final int RIGHT = 0;
    private final int LEFT = 1;
    protected int currentAnimation;

    @Setter
    protected Sprite sprite;
    protected Vector2f position;
    @Getter @Setter
    protected int size;
    @Getter
    protected Animation animation;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected int attackSpeed;
    protected int attackDuration;

    protected float dx;
    protected float dy;

    @Setter
    protected float maxSpeed = 4f;
    @Setter
    protected float acceleration = 3f;
    @Setter
    protected float deAcceleration = 0.3f;

    @Getter
    protected AABB hitBounds;
    @Getter
    protected AABB bounds;

    public Entity(Sprite sprite, Vector2f origin, int size) {
        this.sprite = sprite;
        this.position = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size, size);

        this.animation = new Animation();

        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setAnimation(int i, BufferedImage[] frames, int delay) {
        currentAnimation = i;
        animation.setFrames(frames);
        animation.setDelay(delay);
    }

    public void animate() {
        if (up) {
            if ((currentAnimation != UP || animation.getDelay() == -1)) {
                setAnimation(UP, sprite.getSpriteArray(UP), 5);
            }
        } else if (down) {
            if ((currentAnimation != DOWN || animation.getDelay() == -1)) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 5);
            }
        } else if (left) {
            if ((currentAnimation != LEFT || animation.getDelay() == -1)) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 5);
            }
        } else if (right) {
            if ((currentAnimation != RIGHT || animation.getDelay() == -1)) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 5);
            }
        } else {
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection() {
        if (up) {
            hitBounds.setYOffset(-size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (down) {
            hitBounds.setYOffset(size / 2);
            hitBounds.setXOffset(-size / 2);
        } else if (left) {
            hitBounds.setYOffset(-size);
            hitBounds.setXOffset(0);
        } else if (right) {
            hitBounds.setYOffset(0);
            hitBounds.setXOffset(0);
        }
    }

    public void update() {
        animate();
        setHitBoxDirection();
        animation.update();
    }

    public abstract void render(Graphics2D graphics2D);
    public abstract void input(KeyHandler key, MouseHandler mouse);

}
