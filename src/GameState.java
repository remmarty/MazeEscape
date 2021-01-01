//import BlockType;
//import KeyboardInputListener;
//import Map;
//import Player;

import java.awt.*;
import java.time.LocalTime;

/**
 * Class that manages player movement, game rules, time etc.
 * @author Remigiusz Martyniak
 */
public class GameState {
    /** movement constants */
    final Point DOWN = new Point(0, 1);
    final Point UP = new Point(0, -1);
    final Point LEFT = new Point(-1, 0);
    final Point RIGHT = new Point(1, 0);

    final KeyboardInputListener keyboard;
    final Map map;
    /** fields necessary to manage game state */
    Player player;
    LocalTime timeElapsed;
    State state;
    int collectedKeys;

    /**
     * Class constructor that sets default values when map is reloaded
     * spawns player, resets health, collected keys and time counter
     * @param map
     * @param keyboardListener
     */
    public GameState(Map map, KeyboardInputListener keyboardListener) {
        this.map = map;
        player = new Player(map.getSpawnPoint());
        keyboard = keyboardListener;
        collectedKeys = 0;
        state = State.IN_PROGRESS;
        timeElapsed = LocalTime.of(0, 0, 0);
        player.setHealth(Player.MAX_HEALTH_VALUE);
    }

    /**
     * Verifies whether there is no wall where player wants to move
     * @param relative direction in which player wants to move
     */
    private boolean isAllowedMove(Point relative) {
        Point positionToCheck = (Point) player.getPosition().clone();
        positionToCheck.translate(relative.x, relative.y);
        return map.getBlock(positionToCheck) != BlockType.WALL;
    }

    /** Handling player movement, keeping track of collected keys and updating game state every frame */
    public void update() {

        // movement
        if (keyboard.goDown && isAllowedMove(DOWN)) {
            player.move(DOWN);
        } else if (keyboard.goUp && isAllowedMove(UP)) {
            player.move(UP);
        } else if (keyboard.goLeft && isAllowedMove(LEFT)) {
            player.move(LEFT);
        } else if (keyboard.goRight && isAllowedMove(RIGHT)) {
            player.move(RIGHT);
        }

        // game over state
        if (player.getHealth() <= 0) {
            state = State.DEFEAT;
        }

        // collecting a key make it disappear and resets health pool
        if (collectedKeys < map.getNumOfKeys()) {
            if (map.getBlock(player.getPosition()) == BlockType.KEY) {
                collectedKeys++;
                player.setHealth(Player.MAX_HEALTH_VALUE);
                map.put(player.getPosition(), BlockType.PASSAGE);
            }
        } else {
            // moving to exit when all keys are collected means victory
            if (map.getBlock(player.getPosition()) == BlockType.EXIT) {
                state = State.WIN;
            }
        }
    }

    public int getCollectedKeys() {
        return collectedKeys;
    }

    public void render(Graphics graphics) {
        player.render(graphics);
    }

    public LocalTime getTimeElapsed() { return timeElapsed; }

    public float getPlayerHealth() {
        return player.getHealth();
    }

    public Player getPlayer() {
        return player;
    }

    // three possible states of the game
    enum State {
        IN_PROGRESS,
        WIN,
        DEFEAT,
    }
}
