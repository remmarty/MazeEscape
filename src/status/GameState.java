package status;

import game.MazeEscape;
import units.Player;

import java.awt.*;
import java.time.LocalTime;

public class GameState {

    public static final int MAX_HEALTH_VALUE = 100;
    Player player;
    LocalTime timeElapsed;
    private int playerHealth;

    public int getCollectedKeys() {
        return collectedKeys;
    }

    int collectedKeys = 0;

    public GameState(Point spawnPoint) {
        player = new Player(spawnPoint);
        collectedKeys = 0;
        timeElapsed = LocalTime.of(0, 0, 0);
        playerHealth = MAX_HEALTH_VALUE;
    }

    public void update() {
//        player.update();


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
