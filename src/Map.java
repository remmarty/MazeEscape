import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {
    int mapWidth;
    int mapHeight;
    int numOfKeys = 0;
    Point playerSpawnPoint;
    BlockType[][] blocks;

    public Map(String path) {   // constructor that loads the map
        loadMap(path);
    }

    private void loadMap(String path) {
        String mapFile = MapLoader.loadMap(path);
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
                graphics.drawImage(blockImg, x * Block.WIDTH, y * Block.HEIGHT, Block.WIDTH, Block.HEIGHT, null);
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

    public void put(Point coord, BlockType blockType) {
        blocks[coord.y][coord.x] = blockType;
    }
}
