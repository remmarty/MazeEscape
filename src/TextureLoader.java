import java.awt.image.BufferedImage;

public class TextureLoader {
    // we want to snip 16x16 blocks from png texture sheet
    static final String TEXTURES_FILEPATH = "/textures/textures.png";
    static final int SNIP_WIDTH = 16;
    static final int SNIP_HEIGHT = 16;

    // types of blocks used in game
    public static BufferedImage wall, passage, key, player, exit;

    public static void loadBlocks() {
        BufferedImage textureSheet = ImgLoader.loadImg(TEXTURES_FILEPATH);
        wall = snip(textureSheet, SNIP_WIDTH * 0, 0, SNIP_WIDTH, SNIP_HEIGHT);
        passage = snip(textureSheet, SNIP_WIDTH * 1, 0, SNIP_WIDTH, SNIP_HEIGHT);
        player = snip(textureSheet, SNIP_WIDTH * 2, 0, SNIP_WIDTH, SNIP_HEIGHT);
        key = snip(textureSheet, SNIP_WIDTH * 3, 0, SNIP_WIDTH, SNIP_HEIGHT);
        exit = snip(textureSheet, SNIP_WIDTH * 4, 0, SNIP_WIDTH, SNIP_HEIGHT);
    }

    public static BufferedImage snip(BufferedImage img, int x, int y, int width, int height) {
        return img.getSubimage(x, y, width, height);
    }
}
