package it.avbo.game.entity;

import it.avbo.game.graphics.Sprite;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;

public class Player extends Entity {
    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }

    private void move() {
        if (up) {
            dy -= acceleration;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deAcceleration;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }
        if (down) {
            dy += acceleration;
            if (dy > maxSpeed) {
                dy =  maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deAcceleration;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }
        if (left) {
            dx -= acceleration;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deAcceleration;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (right) {
            dx += acceleration;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deAcceleration;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        move();

        position.x += dx;
        position.y += dy;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(animation.getImage(), (int) (position.x), (int) (position.y), size, size, null);
    }

    @Override
    public void input(KeyHandler key, MouseHandler mouse) {
        if (mouse.getButton() == 1) {
            System.out.println("Player: " + position.x + ", " + position.y);
        }

        up = key.up.down;
        down = key.down.down;
        right = key.right.down;
        left = key.left.down;
    }

}
