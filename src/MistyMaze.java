import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

/**
 * Main component that puts together game mechanics, rendering,
 * responsible for game state, map reloading
 * includes game loop
 * @author Remigiusz Martyniak
 */
public class MistyMaze implements Runnable {
    /**  Setting frames per second */
    final int FPS = 15;
    final long NS_PER_SECOND = 1_000_000_000;
    final long NS_PER_UPDATE = (long) ((1.0d / FPS) * NS_PER_SECOND);
    /** Setting size of visible area around the player */
    final float FOG_SCALE = 1;
    /**  Setting font used in game */
    final Font font = new Font("Impact", Font.PLAIN, 40);

    /** Game window width */
    int width;
    /** Game window height */
    int height;
    /** Game window title */
    String title;
    /** whether game should be running or has to end  */
    boolean running = false;

    BufferStrategy bufferStrategy;
    Graphics graphics;
    Window window;
    Map map;
    GameState gameState = null;
    FinalState state;
    KeyboardInputListener keyboardListener;
    /** order in which maps will be loaded after escape */
    String[] mapPaths = {"/map/Map_1.csv", "/map/Map_2.csv", "/map/Map_3.txt"};
    /** loading necessary GUI textures */
    BufferedImage fog = ImgLoader.loadImg("/textures/fog.png");
    BufferedImage keyImage = ImgLoader.loadImg("/textures/key.png");
    /** keeping track of currently played map */
    int currentMapIndex = 0;

    /**
     * Class constructor that sets game window parameters
     * @param width Game window width
     * @param height Game window height
     * @param title Game window title
     */
    public MistyMaze(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    /**
     *  Triggers update of all subcomponents,
     *  keeps track whether all maps have been completed
     */
    public void update() {
        if (gameState != null) {
            gameState.update();
            switch (gameState.state) {
                case WIN -> {
                    currentMapIndex++;
                    if (currentMapIndex < mapPaths.length) {
                        reloadWithMap(mapPaths[currentMapIndex]);
                        state = FinalState.IN_PROGRESS;
                    } else {
                        state = FinalState.VICTORY;
                    }
                }
                case DEFEAT -> state = FinalState.DEFEAT;
                default -> state = FinalState.IN_PROGRESS;
            }
        }
    }

    /** Renders all game components: GUI, fog, etc. */
    public void render() {
        bufferStrategy = window.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            window.getCanvas().createBufferStrategy(2);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);

        map.render(graphics);
        renderFog();
        graphics.setFont(font);

        // time counter
        switch (state) {
            case IN_PROGRESS -> {
                renderPlayerHealth();
                renderCollectedKeys();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
                graphics.setColor(Color.WHITE);
                graphics.drawString(gameState.getTimeElapsed().format(timeFormatter), 650, 150);
            }
            case VICTORY -> {
                graphics.setColor(Color.green);
                graphics.drawString("VICTORY!", 300, 400);
                running = false;
            }
            case DEFEAT -> {
                graphics.setColor(Color.red);
                graphics.drawString("GAME OVER!", 300, 400);
                running = false;
            }
        }
        gameState.render(graphics);
        bufferStrategy.show();
        graphics.dispose();
    }

    /** Rendering method for key counter only */
    private void renderCollectedKeys() {
        if (gameState.getCollectedKeys() < map.getNumOfKeys()) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.drawString(gameState.getCollectedKeys() + "/" + map.getNumOfKeys(), 650, 100);
        graphics.drawImage(keyImage, 700, 65, 40, 40, null);
    }

    /** Rendering method for player health only */
    private void renderPlayerHealth() {
        if (gameState.getPlayerHealth() > 25) {
            graphics.setColor(Color.GREEN);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawString(Math.round(gameState.getPlayerHealth()) + "/" + (int) Player.MAX_HEALTH_VALUE, 650, 50);
    }

    /** Rendering method for fog only
     *  fog is rendered on player position
     */
    private void renderFog() {
        int width = fog.getWidth();
        int height = fog.getHeight();

        Point playerCenterPixelPos = gameState.getPlayer().getCenterPixelPosition();
        Point playerRelativeFogOffset = new Point(
                (int) (-width / 2 * FOG_SCALE + playerCenterPixelPos.x),
                (int) (-height / 2 * FOG_SCALE + playerCenterPixelPos.y)
        );
        Point scaledDim = new Point(
                (int) (width * FOG_SCALE),
                (int) (height * FOG_SCALE)
        );
        graphics.drawImage(fog, playerRelativeFogOffset.x, playerRelativeFogOffset.y, scaledDim.x, scaledDim.y, null);
    }

    /** Initializing keyboard listener, game window
     *  loads first map and block textures
     */
    public void boot() {
        keyboardListener = new KeyboardInputListener();
        window = new Window(width, height, title, keyboardListener);
        String initialMap = mapPaths[0];
        reloadWithMap(initialMap);
        TextureLoader.loadBlocks();
    }

    /** Loads new map and game state */
    private void reloadWithMap(String mapFilePath) {
        map = new Map(mapFilePath);
        gameState = new GameState(map, keyboardListener);
    }

    /** Game loop */
    public void run() {
        boot();
        running = true;

        long nextTick = System.nanoTime();
        long nextSecond = nextTick;

        while (running) {
            final long now = System.nanoTime();

            // fixed FPS loop
            if (now - nextTick >= 0) {
                update();
                render();
                do {
                    // catch up with as many ticks as needed (skip them if necessary).
                    nextTick += NS_PER_UPDATE;
                } while (now - nextTick >= 0);
            }

            // loop executed every second
            if (now - nextSecond >= 0) {
                nextSecond += NS_PER_SECOND;
                gameState.timeElapsed = gameState.timeElapsed.plusSeconds(1);
            }
        }
    }

    enum FinalState {
        IN_PROGRESS,
        VICTORY,
        DEFEAT
    }
}
