import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Low level deserialization of map files from characters to integers
 * @author Remigiusz Martyniak
 */
public class MapLoader {

    /**
     * Converting BufferReader to a String
     * @param path relative path to resources
     * @return String representation of a file
     */
    public static String readMapFile(String path) {
        StringBuilder builder = new StringBuilder();
        path = MapLoader.class.getResource(path).getPath();
        try {
            BufferedReader bufread = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufread.readLine()) != null)
                builder.append(line + "\n");

            bufread.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * Parse String to int
     * @param number
     * @return integer values
     */
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
