import java.awt.*;

public class Player {
    final double MOVEMENT_HEALTH_PENALTY = 0.5;
    Point position;
    float health = GameState.MAX_HEALTH_VALUE;

    public Player(Point spawnPoint) {
        position = spawnPoint;
    }

    public void move(Point offset) {
        position.translate(offset.x, offset.y);
        if (health > 0) {
            health -= MOVEMENT_HEALTH_PENALTY;
        }
    }

    public void render(Graphics graphics) {
        graphics.drawImage(TextureLoader.player, position.x * Map.BLOCK_WIDTH, position.y * Map.BLOCK_HEIGHT, null);
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(int value) {
        health = value;
    }

    public Point getPosition() {
        return position;
    }

    public Point getCenterPixelPosition() {
        // map position * pixel size of block + half block size
        return new Point(
                position.x * Map.BLOCK_WIDTH + Map.BLOCK_WIDTH / 2,
                position.y * Map.BLOCK_HEIGHT + Map.BLOCK_HEIGHT / 2
        );
    }
}
