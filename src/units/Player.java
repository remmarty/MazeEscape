package units;

import block.Block;
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

    public void move(Point offset) {
        position.translate(offset.x, offset.y);
        System.out.println(position);
    }

    public void render(Graphics graphics) {
       graphics.drawImage(TextureLoader.player, position.x * Block.WIDTH, position.y * Block.HEIGHT, null);
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int healthPoints) {
        health += healthPoints;
    }

    public Point getPosition() {
        return position;
    }
}
