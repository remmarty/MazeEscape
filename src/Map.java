import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Deserializes map file into map object
 * @author Remigiusz Martyniak
 */
public class Map {
    /** every block is 16px wide */
    public static final int BLOCK_WIDTH = 16;
    /** every block is 16px high */
    public static final int BLOCK_HEIGHT = 16;

    int mapWidth;
    int mapHeight;
    int numOfKeys = 0;
    Point playerSpawnPoint;
    BlockType[][] blocks;

    /**
     * constructor that loads the map
     * @param path relative path to the given map
     */
    public Map(String path) {
        loadMap(path);
    }

    /**
     * Loads file from disk by given path
     * infers map size based on amount of rows and first column in file
     * infers amount of keys and player spawn point
     * @param path relative path to resources
     */
    private void loadMap(String path) {
        String mapFile = MapLoader.readMapFile(path);
        String[] rows = mapFile.split("\\n");
        String[] firstRow = rows[0].split("\\s");

        mapHeight = rows.length;
        mapWidth = firstRow.length;
        blocks = new BlockType[mapHeight][mapWidth];

        for (int y = 0; y < mapHeight; y++) {
            String[] currentRow = rows[y].split("\\s");
            for (int x = 0; x < mapWidth; x++) {
                int cellValue = MapLoader.parseInt(currentRow[x]);
                BlockType blockType = BlockType.fromId(cellValue);
                blocks[y][x] = blockType;

                // infer basic map info from data
                if (blockType == BlockType.KEY) {
                    numOfKeys++;
                } else if (blockType == BlockType.PLAYER) {
                    playerSpawnPoint = new Point(x, y);
                }
            }
        }
    }

    /**
     * Draws block textures based on their type
     * @param graphics
     */
    public void render(Graphics graphics) {
        BufferedImage blockImg = null;
        for (int y = 0; y < mapHeight; y++)
            for (int x = 0; x < mapWidth; x++) {
                switch (blocks[y][x]) {
                    case PASSAGE:
                        blockImg = TextureLoader.passage;
                        break;
                    case WALL:
                        blockImg = TextureLoader.wall;
                        break;
                    case KEY:
                        blockImg = TextureLoader.key;
                        break;
                    case EXIT:
                        blockImg = TextureLoader.exit;
                        break;
                }
                graphics.drawImage(blockImg, x * BLOCK_WIDTH, y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT, null);
            }
    }

    public int getNumOfKeys() {
        return numOfKeys;
    }

    public Point getSpawnPoint() {
        return playerSpawnPoint;
    }

    public BlockType getBlock(Point coord) {
        return blocks[coord.y][coord.x];
    }
    /** Utility that assigns block type to specific map coordinate */
    public void put(Point coord, BlockType blockType) {
        blocks[coord.y][coord.x] = blockType;
    }
}
