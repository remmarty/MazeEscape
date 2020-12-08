package status;

import game.MazeEscape;
import tools.TextureLoader;

import java.awt.*;

public abstract class State {

    protected MazeEscape mazeEscape;

    public State(MazeEscape mazeEscape) {
        this.mazeEscape = mazeEscape;
    }

    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }
    public void update() {

    }

    public void render(Graphics graphics) {
        graphics.drawImage(TextureLoader.player, 0, 0, null);
    }
}
