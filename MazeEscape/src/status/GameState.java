package status;

import game.MazeEscape;
import units.Player;

import java.awt.*;
import java.time.LocalTime;

public class GameState extends State {

    public static final int MAX_HEALTH_VALUE = 100;
    private Player player;
    LocalTime timeElapsed;
    private int playerHealth;

    public int getCollectedKeys() {
        return collectedKeys;
    }

    int collectedKeys = 0;

    public GameState(MazeEscape mazeEscape) {
        super(mazeEscape);
        player = new Player(100,100);
        collectedKeys = 0;
        timeElapsed = LocalTime.of(0, 0, 0);
        playerHealth = MAX_HEALTH_VALUE;
    }

    public void update() {
        player.update();
        timeElapsed = timeElapsed.plusSeconds(1);
        if (playerHealth > 0) {
            playerHealth--;
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
}
