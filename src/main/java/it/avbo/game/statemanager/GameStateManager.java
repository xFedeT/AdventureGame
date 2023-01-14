package it.avbo.game.statemanager;

import it.avbo.game.GamePanel;
import it.avbo.game.statemanager.states.*;
import it.avbo.game.util.handler.KeyHandler;
import it.avbo.game.util.handler.MouseHandler;
import it.avbo.game.util.math.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    public static final int PLAY = 0;
    public static final int PAUSE = 1;
    public static final int MENU = 2;
    public static final int SETTINGS = 3;
    public static final int RESPAWN = 4;

    public static Vector2f map;

    public ArrayList<GameState> states;

    public GameStateManager() {
        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        states = new ArrayList<>();
        states.add(new MenuState(this));
    }

    public void pop(int state) {
        states.remove(state);
    }

    public void add(int state) {
        switch (state) {
            case PLAY -> states.add(new PlayState(this));
            case PAUSE -> states.add(new PauseState(this));
            case MENU -> states.add(new MenuState(this));
            case SETTINGS -> states.add(new SettingsState(this));
            case RESPAWN -> states.add(new RespawnState(this));
        }
    }

    public void addAndPop(int state) {
        states.remove(0);
        add(state);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);

        for (GameState state : states) {
            state.update();
        }
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        for (GameState state : states) {
            state.input(mouse, key);
        }
    }

    public void render(Graphics2D graphics2D) {
        for (GameState state : states) {
            state.render(graphics2D);
        }
    }

}
