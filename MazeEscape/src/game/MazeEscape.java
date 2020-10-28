package game;

import utils.ImgLoader;
import window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class MazeEscape implements Runnable {

    public int width, height;
    public String title;
    private boolean running = false;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private BufferedImage textures;
    private Window window;
    private Thread gameThread;
    
    public MazeEscape(int width, int height, String title) {  // game constructor
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void update() {

    }

    public void render() {
        bufferStrategy = window.getCanvas().getBufferStrategy();
        if (bufferStrategy == null){
            window.getCanvas().createBufferStrategy(2);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);
        graphics.drawImage(textures, 100, 100, null);

        bufferStrategy.show();
        graphics.dispose();
    }

    public void init() {
        window = new Window(width, height, title);
        textures = ImgLoader.loadImg("/textures/image.png");
    }

    public void run() {
        init();

        while(running) {
            update();
            render();
        }
        stop();
    }

    public void start() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        if (!running) return;
        running = false;
    }
}
