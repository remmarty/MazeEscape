package units;

import status.GameState;
import tools.TextureLoader;

import java.awt.*;

public class Player {
    Point position;
    int health = GameState.MAX_HEALTH_VALUE;

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;


    public Player(Point spawnPoint) {
        position = spawnPoint;
    }

    public void update() {
    }

    public void render(Graphics graphics) {
       graphics.drawImage(TextureLoader.player, position.x, position.y, null);
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int healthPoints) {
        health += healthPoints;
    }
}
