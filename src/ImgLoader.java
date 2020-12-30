import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImgLoader {

    public static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(ImgLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }
}
