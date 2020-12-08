package block;

public enum BlockType {
    PASSAGE(0),
    WALL(1),
    PLAYER(2),
    KEY(3),
    EXIT(4);

    final int value;

    BlockType(int value) {
        this.value = value;
    }

    public int getId() {
        return value;
    }

    public static BlockType fromId(int id) {
        for (BlockType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
}
