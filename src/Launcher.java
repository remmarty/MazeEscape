
/**
 * Entry point for game to run
 * @author Remigiusz Martyniak
 */
public class Launcher {
    public static void main(String[] args) {
        MazeEscape mazeEscape = new MazeEscape(800, 800, "MazeEscape");
        mazeEscape.run();
    }
}
