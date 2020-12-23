import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

    private int mapWidth;

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    private int mapHeight;
    Point playerSpawnPoint;
    BlockType[][] blocks;
    int numOfKeys = 0;


    public int getNumOfKeys() {
        return numOfKeys;
    }


    public Map(String path) {   // constructor that loads the map
        loadMap(path);
    }

    public void mapRender(Graphics graphics) {
        BufferedImage blockImg = null;
        for (int y = 0; y < mapHeight; y++)
            for(int x = 0; x < mapWidth; x++){
                switch(blocks[y][x]) {
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

    private void loadMap(String path) {
        String mapFile = MapLoader.loadMap(path);
        String[] data = mapFile.split("\\s+");
        mapWidth = MapLoader.parseInt(data[0]);
        mapHeight = MapLoader.parseInt(data[1]);

        blocks = new BlockType[mapHeight][mapWidth];

        for (int y = 0; y < mapHeight; y++)
            for(int x = 0; x < mapWidth; x++){
                int cellValue = MapLoader.parseInt(data[x+y*mapWidth + 2]); // FIXME refactor
                BlockType blockType = BlockType.fromId(cellValue);
                if (blockType == BlockType.KEY) {
                    numOfKeys++;
                } else if (blockType == BlockType.PLAYER) {
                    playerSpawnPoint = new Point(x, y);
                }
                blocks[y][x] = blockType;
            }
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
