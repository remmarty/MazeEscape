//import KeyboardInputListener;

import javax.swing.*;
import java.awt.*;

public class Window {

        private JFrame frame;
        private Canvas canvas;
        private int width, height;
        private String title;
       // public Font timerFont = new Font("Times New Roman", Font.PLAIN, 70);

        public Window(int width, int height, String title, KeyboardInputListener keyboardListener) {
            this.width = width;
            this.height = height;
            this.title = title;
            frame = initFrame();
            canvas = initCanvas(keyboardListener);
            frame.add(canvas);
            frame.pack();
        }

        private JFrame initFrame() {       // method that manages our window and canvas

            frame = new JFrame(title);          // setting the frame
            frame.setLocationRelativeTo(null);
            frame.setSize(width, height);
            frame.setTitle(title);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//            frame.add(new JLabel("Double Click to Add Text"), BorderLayout.NORTH);

//          counterLabel.setBounds(300, 230, 200, 100);
//          counterLabel.setHorizontalAlignment((JLabel.CENTER));
//          counterLabel.setFont(timerFont);
//          counterLabel.setText("");
//          frame.add(counterLabel);
            frame.setVisible(true);
            return frame;
        }

    private Canvas initCanvas(KeyboardInputListener listener) {
        canvas = new Canvas();          // setting the canvas
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(true);
        canvas.addKeyListener(listener);
        return canvas;
    }

    public JFrame getFrame() { return frame; }
        public Canvas getCanvas(){
            return canvas;
        }

}
