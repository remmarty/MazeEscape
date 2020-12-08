package tools;

import java.awt.image.BufferedImage;

public class TextureSheet {

    private final BufferedImage imgBlock;

    public TextureSheet(BufferedImage imgBlock) {
        this.imgBlock = imgBlock;
    }
    public BufferedImage snip(int x, int y, int width, int height) {
        return imgBlock.getSubimage(x, y, width, height);
    }
}