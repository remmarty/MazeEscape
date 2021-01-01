import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class that implements KeyListener and handles keyboard input
 * @author Remigiusz Martyniak
 */
public class KeyboardInputListener implements KeyListener {
    /** max number of keys that can be simultaneously used */
    static final int KEYS = 104;
    final boolean[] key;
    boolean goRight, goLeft, goUp, goDown;

    public KeyboardInputListener() {
        key = new boolean[KEYS];
    }

    /** WSAD movement */
    private void updateWSAD() {
        goRight = key[KeyEvent.VK_D];
        goLeft = key[KeyEvent.VK_A];
        goUp = key[KeyEvent.VK_W];
        goDown = key[KeyEvent.VK_S];
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Registers key pressed event and sets element corresponding to that keycode in array to true
     * @param e keyboard event
     */
    public void keyPressed(KeyEvent e) {
        key[e.getKeyCode()] = true;
        updateWSAD();
    }

    /**
     * Registers key release event and sets element corresponding to that keycode in array to false
     * @param e keyboard event
     */
    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
        updateWSAD();
    }
}
