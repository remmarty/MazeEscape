import javax.swing.*;
import java.awt.*;

public class Window {
    JFrame frame;
    Canvas canvas;
    int width, height;
    String title;

    public Window(int width, int height, String title, KeyboardInputListener keyboardListener) {
        this.width = width;
        this.height = height;
        this.title = title;
        frame = initFrame();
        canvas = initCanvas(keyboardListener);
        frame.add(canvas);
        frame.pack();
    }

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
