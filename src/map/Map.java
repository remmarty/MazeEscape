package map;

import block.Block;
import block.BlockFixme;
import tools.MapLoader;

import java.awt.*;

public class Map {

    private int mapWidth;
    private int mapHeight;
    Point playerSpawnPoint;
    private int[][] blocks;

    public int getNumOfKeys() {
        return numOfKeys;
    }

    int numOfKeys = 0;

    public Map(String path) {   // constructor that loads the map
        loadMap(path);
    }

    public void mapRender(Graphics graphics) {
        for (int j = 0; j < mapHeight; j++)
            for(int i = 0; i < mapWidth; i++){
                Block block = getBlock(i, j);

//                if (block.getBlockID() == BlockFixme.PLAYER.getValue()) {
//
//                }
                block.render(graphics, i  * Block.blockWidth , j   * Block.blockHeight);
            }
    }

    public Block getBlock(int i, int j) {
        Block block = Block.blocks[blocks[i][j]];
        if (block == null)
            return Block.wallBlock;
        return block;
    }

    private void loadMap(String path) {
        String mapFile = MapLoader.loadMap(path);
        String[] data = mapFile.split("\\s+");
        mapWidth = MapLoader.parseInt(data[0]);
        mapHeight = MapLoader.parseInt(data[1]);

        blocks = new int[mapWidth][mapHeight];

        for (int y = 0; y < mapHeight; y++)
            for(int x = 0; x < mapWidth; x++){
                int cellValue = MapLoader.parseInt(data[x+y*mapWidth + 2]); // FIXME refactor
                if (cellValue == BlockFixme.KEY.getValue()) {
                    numOfKeys++;
                } else if (cellValue == BlockFixme.PLAYER.getValue()) {
                    playerSpawnPoint = new Point(x, y);
                }
                blocks[x][y] = cellValue;
            }
    }

    public Point getSpawnPoint() {
        return playerSpawnPoint;
    }
}
