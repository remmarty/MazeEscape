package status;

import game.KeyboardInputListener;
import units.Player;

import java.awt.*;
import java.time.LocalTime;

public class GameState {

    public static final int MAX_HEALTH_VALUE = 100;
    private final KeyboardInputListener keyboard;
    Player player;
    LocalTime timeElapsed;
    private int playerHealth;

    public int getCollectedKeys() {
        return collectedKeys;
    }

    int collectedKeys = 0;

    public GameState(Point spawnPoint, KeyboardInputListener keyboardListener) {
        player = new Player(spawnPoint);
        keyboard = keyboardListener;
        collectedKeys = 0;
        timeElapsed = LocalTime.of(0, 0, 0);
        playerHealth = MAX_HEALTH_VALUE;
    }

    public void update() {
//        player.update();
        if (keyboard.goDown) {
            player.move(new Point(0, 1));
        } else if (keyboard.goUp) {
            player.move(new Point(0, -1));
        } else if (keyboard.goLeft) {
            player.move(new Point(-1, 0));
        } else if (keyboard.goRight) {
            player.move(new Point(1, 0));
        }
        timeElapsed = timeElapsed.plusSeconds(1);

        if (player.getHealth() > 0) {
            player.addHealth(-1);
        }
    }

    public void render(Graphics graphics) {
        player.render(graphics);
    }

    public LocalTime getTimeElapsed() {
        return timeElapsed;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

//    public Player spawnPlayer(Point spawnPoint) {
//        player = ;
//    }
}
