package 



public class Ship {
    private final String name;
    private final int length;
    private Position position;
    private Direction direction;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.position = null;
        this.direction = null;
    }
    public int size() {
        return length;
    }

    public String name() {
        return name;
    }

}
