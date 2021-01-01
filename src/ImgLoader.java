import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class that safely load image from the disk
 * @author Remigusz Martyniak
 */
public class ImgLoader {

    /**
     * @param path relative path to used in game .png images (textures)
     * @return null if there was a problem with loading an image
     */
    public static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(ImgLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
