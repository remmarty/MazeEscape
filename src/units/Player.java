package units;

import tools.TextureLoader;

import java.awt.*;

public class Player extends Unit {

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;


    public Player(float x, float y) {
        super(x, y);
    }

    public void update() {

    }

    public void render(Graphics graphics) {
       graphics.drawImage(TextureLoader.player,(int) x,(int) y, null);
    }
}
