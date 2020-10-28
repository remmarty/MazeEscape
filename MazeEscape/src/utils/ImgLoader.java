package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImgLoader {    // method that will load our .png textures

    public static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(ImgLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } return null;
    }
}
