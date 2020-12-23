import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

public class MazeEscape implements Runnable {
    final int FPS = 15;
    final long NS_PER_UPDATE = (long)((1.0d/FPS) * 1000000000);
    final long NS_PER_SECOND = 1000000000;

    public int width, height;
    public String title;
    private boolean running = false;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage textures;
    private Window window;
    private Map map;
    private GameState gameState = null;
    private KeyboardInputListener keyboardListener;
    Font font = new Font("Impact", Font.PLAIN, 40);

    String[] mapPaths = {"/map/Map_3.txt", "/map/Map_4.txt", "/map/Map_3.txt"};
    int currentMapIndex = 0;
    enum FinalState {
        IN_PROGRESS,
        VICTORY,
        DEFEAT
    };
    FinalState state = FinalState.IN_PROGRESS;

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
            }
        }
    }

    public void render() {
        bufferStrategy = window.getCanvas().getBufferStrategy();
        if (bufferStrategy == null){
            window.getCanvas().createBufferStrategy(2);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);
//        Block.blocks[0].render(graphics, 0,0);
        map.mapRender(graphics);


        /* Create an ARGB BufferedImage */
        BufferedImage fog = ImgLoader.loadImg("/textures/fog.png");
        int width = fog.getWidth();
        int height = fog.getHeight();
        // TODO refactor position to gamestate
//        graphics.drawImage(fog, -width/2 + gameState.getPlayer().position.x * Block.WIDTH + Block.WIDTH / 2, -height/2 + gameState.getPlayer().position.y * Block.HEIGHT + Block.HEIGHT/2, width, height, null);

        graphics.setFont(font);

        switch (state) {
            case IN_PROGRESS:
                if (gameState.getPlayerHealth() > 50) {
                    graphics.setColor(Color.GREEN);
                } else {
                    graphics.setColor(Color.RED);
                }
                graphics.drawString(gameState.getPlayerHealth() + "/" + GameState.MAX_HEALTH_VALUE, 650, 50);

                if (gameState.getCollectedKeys() < map.getNumOfKeys()) {
                    graphics.setColor(Color.YELLOW);
                } else {
                    graphics.setColor(Color.GREEN);
                }
                graphics.drawString(gameState.getCollectedKeys() + "/" + map.getNumOfKeys(), 650, 100);

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
                graphics.setColor(Color.WHITE);
                graphics.drawString(gameState.getTimeElapsed().format(timeFormatter), 650, 150);

                // FIXME Load once
                BufferedImage keyImage = ImgLoader.loadImg("/textures/key.png");
                graphics.drawImage(keyImage, 710, 65, 40, 40, null);
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

//        BufferedImage resizedImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
//        graphics2D.dispose();

//        if(gameState != null){
        gameState.render(graphics);
//        }
        bufferStrategy.show();
        graphics.dispose();

    }

    public void boot() {
        keyboardListener = new KeyboardInputListener();
        window = new Window(width, height, title, keyboardListener);
        String initialMap = mapPaths[0];
        reloadWithMap(initialMap);
        TextureLoader.loadBlocks();
        textures = ImgLoader.loadImg("/textures/textures.png");
    }

    private void reloadWithMap(String mapFilePath) {
        map = new Map(mapFilePath);
        gameState = new GameState(map, keyboardListener);
        // FIXME
//        window.resize(map.getMapWidth() * Block.WIDTH, map.getMapHeight() * Block.HEIGHT);
    }

    public void run() {
        boot();

        long secondsPassed = 0;
        long nextTick = System.nanoTime();
        long nextSecond = nextTick;
        long nextRender = nextTick;

        while (running) {


            final long now = System.nanoTime();

            if (now - nextTick >= 0) {
                update();
                render();
                // catch up with as many ticks as needed (skip them if necessary).
                do {
                    nextTick += NS_PER_UPDATE;
                } while (now - nextTick >= 0);
            }

            if (now - nextSecond >= 0) {
                // catch up with as many ticks as needed (no skipping!).
                nextSecond += NS_PER_SECOND;
                secondsPassed++;
                System.out.println((float)(now - nextSecond) / 1000000000);
                gameState.timeElapsed = gameState.timeElapsed.plusSeconds(1);
            }

            // calculate the delay to the next event.....
            final long workTime = System.nanoTime();
            final long minDelay = Math.min(nextSecond - workTime,
                    Math.min(nextTick - workTime, nextRender - workTime));

            if (minDelay > 0) {
                //next event is no due yet. Yield the system....
                // to some point after the next delay... (millisecond precision)
                long milliDelay = (minDelay + 1_000_000) / 1_000_000L;
                try {
                    Thread.sleep(milliDelay);
                } catch (InterruptedException ie) {
                    // ignore.
                }
            }
        }
    }

    public void start() {
        if (running) return;
        running = true;
//        gameThread = new Thread(this);
//        gameThread.start();
    }

    public void stop() {
        if (!running) return;
        running = false;
    }
}
