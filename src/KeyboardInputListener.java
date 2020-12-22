import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputListener implements KeyListener {
    private static final int KEYS = 256;
    private final boolean[] key;
    public boolean goRight, goLeft, goUp, goDown;

    public KeyboardInputListener() {
        key = new boolean[KEYS];
    }

    private void update() {
        goRight = key[KeyEvent.VK_D];
        goLeft = key[KeyEvent.VK_A];
        goUp = key[KeyEvent.VK_W];
        goDown = key[KeyEvent.VK_S];
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        key[e.getKeyCode()] = true;
        update(); // FIXME
    }

    public void keyReleased(KeyEvent e) {
        key[e.getKeyCode()] = false;
        update(); // FIXME
    }
}
