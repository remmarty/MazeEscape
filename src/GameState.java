//import BlockType;
//import KeyboardInputListener;
//import Map;
//import Player;

import java.awt.*;
import java.time.LocalTime;

public class GameState {
    //  FIXME refactor to another class
    private final Point DOWN = new Point(0, 1);
    private final Point UP = new Point(0, -1);
    private final Point LEFT = new Point(-1, 0);
    private final Point RIGHT = new Point(1, 0);

    public static final int MAX_HEALTH_VALUE = 100;
    private final KeyboardInputListener keyboard;
    private final Map map;
    Player player;
    LocalTime timeElapsed;

    public int getCollectedKeys() {
        return collectedKeys;
    }

    int collectedKeys = 0;

    public GameState(Map map, KeyboardInputListener keyboardListener) {
        this.map = map;
        player = new Player(map.getSpawnPoint());
        keyboard = keyboardListener;
        collectedKeys = 0;
        timeElapsed = LocalTime.of(0, 0, 0);
        player.setHealth(MAX_HEALTH_VALUE);
    }

    private boolean isAllowedMove(Point relative) {
        Point positionToCheck = (Point) player.getPosition().clone();
        positionToCheck.translate(relative.x, relative.y);
        return map.getBlock(positionToCheck) != BlockType.WALL;
    }

    public void update() {
//        player.update();
//        System.out.println(player.getPosition());
        if (keyboard.goDown && isAllowedMove(DOWN)){
            player.move(DOWN);
        } else if (keyboard.goUp && isAllowedMove(UP)) {
            player.move(UP);
        } else if (keyboard.goLeft && isAllowedMove(LEFT)) {
            player.move(LEFT);
        } else if (keyboard.goRight && isAllowedMove(RIGHT)) {
            player.move(RIGHT);
        }

        if (collectedKeys < map.getNumOfKeys()) {
            if (map.getBlock(player.getPosition()) == BlockType.KEY) {
                collectedKeys++;
                System.out.println("key collected");
                map.put(player.getPosition(), BlockType.PASSAGE);
            }
        } else {
            System.out.println("win"); // FIXME refactor, end state
        }


    }

    public void render(Graphics graphics) {
        player.render(graphics);
    }

    public LocalTime getTimeElapsed() {
        return timeElapsed;
    }

    public int getPlayerHealth() {
        return player.getHealth();
    }

    public Player getPlayer() {
        return player;
    }

//    public Player spawnPlayer(Point spawnPoint) {
//        player = ;
//    }
}
