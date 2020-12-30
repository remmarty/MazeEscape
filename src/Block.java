import java.awt.*;
import java.awt.image.BufferedImage;


// TODO Make Enum instead
public class Block {        // block class

    public static final int WIDTH = 16;  // every block is 16x16 pixels
    public static final int HEIGHT = 16;
    private final BufferedImage blockImg;
    private final int blockID;

    public static Block[] blocks = new Block[2500];

    public Block(BufferedImage blockImg, int blockID) {    // block constructor
        blocks[blockID] = this;
        this.blockImg = blockImg;
        this.blockID = blockID;     // every block has it image and ID number
    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(blockImg, x, y, WIDTH, HEIGHT, null);
    }

    public int getBlockID() {
        return blockID;
    }
}
