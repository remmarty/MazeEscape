package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputListener implements KeyListener {

    private static final int KEYS = 256;
    private final boolean[] key;
    public boolean goRight, goLeft, goUP, goDown;

    public KeyboardInputListener() {
        key = new boolean[KEYS];
    }

    public void update() {
        goRight = key[KeyEvent.VK_W];
        goLeft = key[KeyEvent.VK_S];
        goUP = key[KeyEvent.VK_A];
        goDown = key[KeyEvent.VK_D];
    }
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
//        System.out.println("Pressed");
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        //key[e.getKeyCode()] = true;
//        int key = e.getKeyCode();
//
//        switch(key) {
//            case KeyEvent.VK_UP:
//                break;
//            case KeyEvent.VK_DOWN:
    }


    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
    }
}
