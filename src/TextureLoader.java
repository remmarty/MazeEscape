import java.awt.image.BufferedImage;

/**
 * Snipping subimages (textures) from texture .png file
 * @author Remigiusz Martyniak
 */
public class TextureLoader {
    /** We want to snip 16x16 blocks from texture sheet */
    static final String TEXTURES_FILEPATH = "/textures/textures.png";
    static final int SNIP_WIDTH = 16;
    static final int SNIP_HEIGHT = 16;

    /** Types of blocks used in game */
    public static BufferedImage wall, passage, key, player, exit;


    /**
     * Loading texture sheet and assigning texture to proper block type
     */
    public static void loadBlocks() {
        BufferedImage textureSheet = ImgLoader.loadImg(TEXTURES_FILEPATH);
        wall = snip(textureSheet, SNIP_WIDTH * 0, 0, SNIP_WIDTH, SNIP_HEIGHT);
        passage = snip(textureSheet, SNIP_WIDTH * 1, 0, SNIP_WIDTH, SNIP_HEIGHT);
        player = snip(textureSheet, SNIP_WIDTH * 2, 0, SNIP_WIDTH, SNIP_HEIGHT);
        key = snip(textureSheet, SNIP_WIDTH * 3, 0, SNIP_WIDTH, SNIP_HEIGHT);
        exit = snip(textureSheet, SNIP_WIDTH * 4, 0, SNIP_WIDTH, SNIP_HEIGHT);
    }

    /**
     * Snipping subimages
     * @param img loaded texture sheet
     * @param x snip width
     * @param y snip height
     * @param width of texture sheet
     * @param height of texture sheet
     * @return image of wanted block type
     */
    public static BufferedImage snip(BufferedImage img, int x, int y, int width, int height) {
        return img.getSubimage(x, y, width, height);
    }
}
