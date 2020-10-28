package window;

import javax.swing.*;
import java.awt.*;

    public class Window {

        private Canvas canvas;
        private JFrame frame;
        private int width, height;
        private String title;

        public Window(int width, int height, String title) {
            this.width = width;
            this.height = height;
            this.title = title;
            Display();
        }

        private void Display() {       // method that manages our window and canvas

            frame = new JFrame(title);
            frame.setLocationRelativeTo(null);
            frame.setSize(width, height);
            frame.setTitle(title);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(width, height));
            canvas.setMaximumSize(new Dimension(width, height));
            canvas.setMinimumSize(new Dimension(width, height));
            canvas.setFocusable(false);
            frame.add(canvas);
            frame.pack();
        }

        public Canvas getCanvas(){
            return canvas;
        }
}
