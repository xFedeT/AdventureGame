package it.avbo.game.managers.states;

import it.avbo.game.managers.GameStateManager;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;

import java.awt.*;

public abstract class GameState {
    private GameStateManager gameStateManager;

    public GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D graphics2D);

}
