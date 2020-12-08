package game;

import block.Block;
import map.Map;
import status.GameState;
import tools.ImgLoader;
import tools.TextureLoader;
import units.Player;
import window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;

public class MazeEscape implements Runnable {

    public static int FPS = 30;
    public int width, height;
    public String title;
    private boolean running = false;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage textures;
    private Window window;
    private Thread gameThread;
    private Map map;
    private GameState gameState = null;
    private KeyboardInputListener keyboardListener;

    public MazeEscape(int width, int height, String title) {  // game constructor
        this.width = width;
        this.height = height;
        this.title = title;

    }


    public void update() {
        if(gameState != null){
            gameState.update();
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
//        graphics.drawImage(fog, -824 + FPS, -664, 2500,2500, null);
//        FPS+=2;
//        System.out.println(player.getX() + " " + player.getY());

        /*
         * Create a rescale filter op that makes the image
         * 50% opaque.
         */
//        float[] scales = { 1f, 1f, 1f, 0.5f };
//        float[] offsets = new float[4];
//        RescaleOp rop = new RescaleOp(scales, offsets, null);
//
//        /* Draw the image, applying the filter */
//        g2d.drawImage(bi, rop, 0, 0);
        // FIXME Declare once somewhere
        Font font = new Font("Impact", Font.PLAIN, 40);
        graphics.setFont(font);

        if (gameState.getPlayerHealth() > 50) {
            graphics.setColor(Color.GREEN);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.drawString(gameState.getPlayerHealth() + "/" + GameState.MAX_HEALTH_VALUE, 650, 50);

        graphics.setColor(Color.YELLOW);
        graphics.drawString(gameState.getCollectedKeys() + "/" + map.getNumOfKeys(), 650, 100);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("mm:ss");
        graphics.setColor(Color.WHITE);
        graphics.drawString(gameState.getTimeElapsed().format(timeFormatter), 650, 150);

        BufferedImage keyImage = ImgLoader.loadImg("/textures/key.png");

//        BufferedImage resizedImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics.drawImage(keyImage, 710, 65, 40, 40, null);
//        graphics2D.dispose();

//        if(gameState != null){
        gameState.render(graphics);
//        }
        bufferStrategy.show();
        graphics.dispose();

    }

    public void boot() {

        //player = new Player(100,100);
        map = new Map("/map/Map_3.txt");
        keyboardListener = new KeyboardInputListener();
        gameState = new GameState(map, keyboardListener);
        window = new Window(width, height, title, keyboardListener);
        TextureLoader.loadBlocks();
      //  player.render(graphics);

       textures = ImgLoader.loadImg("/textures/textures.png");
    }

    public void run() {
        boot();
        long timer = 0;
        int ticks = 0;

        // TODO cap frame rate somehow
//        System.currentTimeMillis() > lastTickTime
//        double lastTickTime = System.currentTimeMillis();
        while(running) {
            update();
            render();
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
