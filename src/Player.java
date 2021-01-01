import java.awt.*;

/**
 * Represents current player state: health, map position
 * @author Remigiusz Martyniak
 */
public class Player {

    /** Health cost of one player move */
    final double MOVEMENT_HEALTH_PENALTY = 0.5;
    /** Player health pool */
    public static final float MAX_HEALTH_VALUE = 100;
    /** Player current health */
    static float health = MAX_HEALTH_VALUE;
    /** Player position */
    Point position;

    public Player(Point spawnPoint) {
        position = spawnPoint;
    }

    /**
     * Every move decreases player health
     * @param offset relative vector by which player wants to move
     */
    public void move(Point offset) {
        position.translate(offset.x, offset.y);
        if (health > 0) {
            health -= MOVEMENT_HEALTH_PENALTY;
        }
    }

    /**
     * Rendering player
     * @param graphics drawing buffer
     */
    public void render(Graphics graphics) {
        graphics.drawImage(TextureLoader.player, position.x * Map.BLOCK_WIDTH, position.y * Map.BLOCK_HEIGHT, null);
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float value) {
        health = value;
    }

    public Point getPosition() {
        return position;
    }

    /**
     * @return player center position on the map
     */
    public Point getCenterPixelPosition() {
        // map position * pixel size of block + half block size
        return new Point(
                position.x * Map.BLOCK_WIDTH + Map.BLOCK_WIDTH / 2,
                position.y * Map.BLOCK_HEIGHT + Map.BLOCK_HEIGHT / 2
        );
    }
}
