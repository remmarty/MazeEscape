import javax.swing.*;
import java.awt.*;

/**
 * Window and Canvas of the game
 * @author Remigiusz Martyniak
 */

public class Window {
    JFrame frame;
    Canvas canvas;
    /** game window parameters */
    int width, height;
    String title;

    /**
     * Class constructor that sets parameters and attaches
     * canvas with keyboard listener to the window
     * @param width window width
     * @param height window height
     * @param title window title
     * @param keyboardListener keyboard listener attached to window through canvas
     */
    public Window(int width, int height, String title, KeyboardInputListener keyboardListener) {
        this.width = width;
        this.height = height;
        this.title = title;
        frame = initFrame();
        canvas = initCanvas(keyboardListener);
        frame.add(canvas);
        frame.pack();
    }

    /** Setting up frame */
    private JFrame initFrame() {
        frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Setting up canvas
     * @param listener keyboard listener attached to canvas
     * @return canvas
     */
    private Canvas initCanvas(KeyboardInputListener listener) {
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        canvas.addKeyListener(listener);
        return canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
