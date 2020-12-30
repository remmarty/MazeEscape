import java.awt.image.BufferedImage;

public class TextureLoader {

    static final int snipWidth = 16;   // we want to snip 16x16 blocks from png texture sheet
    static final int snipHeight = 16;

    public static BufferedImage wall, passage, key, player, exit;    // types of blocks used in game

    public static void loadBlocks() {
        TextureSheet textures = new TextureSheet(ImgLoader.loadImg("/textures/textures.png")); // create and load texture sheet object
        wall = textures.snip(0, 0, snipWidth, snipHeight);
        passage = textures.snip(snipWidth, 0, snipWidth, snipHeight);
        player = textures.snip(snipWidth * 2, 0, snipWidth, snipHeight);
        key = textures.snip(snipWidth * 3, 0, snipWidth, snipHeight);
        exit = textures.snip(snipWidth * 4, 0, snipWidth, snipHeight);
    }
}
