
/**
 * Entry point for game to run
 * @author Remigiusz Martyniak
 */
public class Launcher {
    public static void main(String[] args) {
        MistyMaze mistyMaze = new MistyMaze(800, 800, "MistyMaze");
        mistyMaze.run();
    }
}
