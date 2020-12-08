package window;

import game.MazeEscape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

        private JFrame frame;
        private Canvas canvas;
        private int width, height;
        private String title;
       // public Font timerFont = new Font("Times New Roman", Font.PLAIN, 70);

        public Window(int width, int height, String title) {
            this.width = width;
            this.height = height;
            this.title = title;
            Display();
        }

        private void Display() {       // method that manages our window and canvas

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

            canvas = new Canvas();          // setting the canvas
            canvas.setPreferredSize(new Dimension(width, height));
            canvas.setMaximumSize(new Dimension(width, height));
            canvas.setMinimumSize(new Dimension(width, height));
            canvas.setFocusable(false);

            frame.add(canvas);

            frame.pack();
        }

        public JFrame getFrame() { return frame; }
        public Canvas getCanvas(){
            return canvas;
        }

}
