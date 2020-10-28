package game;
import window.Window;

public class Launcher {

    public static void main(String[] args) {

        MazeEscape mazeEscape = new MazeEscape(800, 800, "MazeEscape"); // create window
        mazeEscape.start();
    }
}
