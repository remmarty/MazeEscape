package block;

import tools.TextureLoader;

public class WallBlock extends Block {
    public WallBlock(int blockID) {
        super(TextureLoader.wall, blockID);
    }
}
