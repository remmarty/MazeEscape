/**
 * Represents types of blocks based on their value in a map file
 * @author Remigiusz Martyniak
 */
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

    /**
     * Converts value from map file to object
     * @param id numeric value from map
     */
    public static BlockType fromId(int id) {
        for (BlockType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return value;
    }
}
