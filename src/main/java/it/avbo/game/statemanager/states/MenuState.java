package it.avbo.game.statemanager.states;

import it.avbo.game.entity.Player;
import it.avbo.game.graphics.Font;
import it.avbo.game.graphics.Sprite;
import it.avbo.game.statemanager.GameState;
import it.avbo.game.statemanager.GameStateManager;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;

public class MenuState extends GameState {

    private final Font font;
    private final Player player;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        font = new Font("font/ZeldaFont.png", 16, 16);
        player = new Player(new Sprite("entity/linkformatted.png"), new Vector2f(300, 300), 128);
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(key, mouse);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        Sprite.drawArray(graphics2D, font, "A", new Vector2f(100, 100), 32, 32, 16, 0);
        player.render(graphics2D);
    }
}
