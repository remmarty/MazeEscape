import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

public class MazeEscape implements Runnable {
    final int FPS = 15;
    final long NS_PER_SECOND = 1_000_000_000;
    final long NS_PER_UPDATE = (long) ((1.0d / FPS) * NS_PER_SECOND);
    final float FOG_SCALE = 2;
    final Font font = new Font("Impact", Font.PLAIN, 40);

    public int width, height;
    public String title;
    boolean running = false;

    BufferStrategy bufferStrategy;
    Graphics graphics;
    Window window;
    Map map;
    GameState gameState = null;
    FinalState state;
    KeyboardInputListener keyboardListener;
    String[] mapPaths = {"/map/Map_1.csv", "/map/Map_2.csv", "/map/Map_3.txt"};
    BufferedImage fog = ImgLoader.loadImg("/textures/fog.png");
    BufferedImage keyImage = ImgLoader.loadImg("/textures/key.png");

    int currentMapIndex = 0;

    public MazeEscape(int width, int height, String title) {  // game constructor
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void update() {
        if (gameState != null) {
            gameState.update();
            switch (gameState.state) {
                case WIN:
                    currentMapIndex++;
                    if (currentMapIndex < mapPaths.length) {
                        reloadWithMap(mapPaths[currentMapIndex]);
                        state = FinalState.IN_PROGRESS;
                    } else {
                        state = FinalState.VICTORY;
                    }
                    break;
                case DEFEAT:
                    state = FinalState.DEFEAT;
                    break;
                default:
                    state = FinalState.IN_PROGRESS;
            }
        }
    }

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

        switch (state) {
            case IN_PROGRESS:
                renderPlayerHealth();
                renderCollectedKeys();

                // time counter
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
                graphics.setColor(Color.WHITE);
                graphics.drawString(gameState.getTimeElapsed().format(timeFormatter), 650, 150);
                break;
            case VICTORY:
                graphics.setColor(Color.green);
                graphics.drawString("VICTORY!", 300, 400);
                running = false;
                break;
            case DEFEAT:
                graphics.setColor(Color.red);
                graphics.drawString("GAME OVER!", 300, 400);
                running = false;
                break;
        }
        gameState.render(graphics);
        bufferStrategy.show();
        graphics.dispose();
    }

    private void renderCollectedKeys() {
        if (gameState.getCollectedKeys() < map.getNumOfKeys()) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.drawString(gameState.getCollectedKeys() + "/" + map.getNumOfKeys(), 650, 100);
        graphics.drawImage(keyImage, 700, 65, 40, 40, null);
    }

    private void renderPlayerHealth() {
        if (gameState.getPlayerHealth() > 25) {
            graphics.setColor(Color.GREEN);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawString(Math.round(gameState.getPlayerHealth()) + "/" + GameState.MAX_HEALTH_VALUE, 650, 50);
    }

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

    public void boot() {
        keyboardListener = new KeyboardInputListener();
        window = new Window(width, height, title, keyboardListener);
        String initialMap = mapPaths[0];
        reloadWithMap(initialMap);
        TextureLoader.loadBlocks();
    }

    private void reloadWithMap(String mapFilePath) {
        map = new Map(mapFilePath);
        gameState = new GameState(map, keyboardListener);
    }

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
