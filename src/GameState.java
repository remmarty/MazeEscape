//import BlockType;
//import KeyboardInputListener;
//import Map;
//import Player;

import java.awt.*;
import java.time.LocalTime;

public class GameState {
    enum State {
        IN_PROGRESS,
        WIN,
        DEFEAT,
    }
    // movement constants
    final Point DOWN = new Point(0, 1);
    final Point UP = new Point(0, -1);
    final Point LEFT = new Point(-1, 0);
    final Point RIGHT = new Point(1, 0);

    final KeyboardInputListener keyboard;
    final Map map;

    public static final int MAX_HEALTH_VALUE = 100;

    Player player;
    LocalTime timeElapsed;
    State state;
    int collectedKeys;

    public GameState(Map map, KeyboardInputListener keyboardListener) {
        this.map = map;
        player = new Player(map.getSpawnPoint());
        keyboard = keyboardListener;
        collectedKeys = 0;
        state = State.IN_PROGRESS;
        timeElapsed = LocalTime.of(0, 0, 0);
        player.setHealth(MAX_HEALTH_VALUE);
    }

    private boolean isAllowedMove(Point relative) {
        Point positionToCheck = (Point) player.getPosition().clone();
        positionToCheck.translate(relative.x, relative.y);
        return map.getBlock(positionToCheck) != BlockType.WALL;
    }

    public void update() {
        // keyboard movement
        if (keyboard.goDown && isAllowedMove(DOWN)){
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

        if (collectedKeys < map.getNumOfKeys()) {
            if (map.getBlock(player.getPosition()) == BlockType.KEY) {
                collectedKeys++;
                player.setHealth(MAX_HEALTH_VALUE);
                System.out.println("key collected");
                map.put(player.getPosition(), BlockType.PASSAGE);
            }
        } else {
            // all keys are collected
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

    public LocalTime getTimeElapsed() {
        return timeElapsed;
    }

    public float getPlayerHealth() {
        return player.getHealth();
    }

    public Player getPlayer() {
        return player;
    }
}
