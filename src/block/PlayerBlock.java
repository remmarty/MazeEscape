package block;

import tools.TextureLoader;

public class PlayerBlock extends Block {
    public PlayerBlock(int blockID) {
        super(TextureLoader.player, blockID);
    }
}
