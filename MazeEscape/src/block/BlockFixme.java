package block;

public enum BlockFixme {
    PASSAGE(0),
    WALL(1),
    PLAYER(2),
    KEY(3),
    EXIT(4);

    final int value;

    private BlockFixme(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
