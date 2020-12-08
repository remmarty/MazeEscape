package map;

import block.Block;
import block.BlockFixme;
import block.KeyBlock;
import tools.MapLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {

    private int mapWidth;
    private int mapHeight;
    private int playerX;
    private int playerY;
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
        playerX = MapLoader.parseInt(data[2]);
        playerY = MapLoader.parseInt(data[3]);

        blocks = new int[mapWidth][mapHeight];

        for (int j = 0; j < mapHeight; j++)
            for(int i = 0; i < mapWidth; i++){
                int cellValue = MapLoader.parseInt(data[i+j*mapWidth + 4]);
                if (cellValue == BlockFixme.KEY.getValue()) {
                    numOfKeys++;
                }
                blocks[i][j] = cellValue;
            }
    }
}
