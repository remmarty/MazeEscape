import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public static String loadMap(String path) {
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

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
