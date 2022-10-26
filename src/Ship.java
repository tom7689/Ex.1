package 



public class Ship {
    private final String name;
    private final int length;
    private Position position;
    private Orientation orientation;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.position = null;
        this.orientation = null;
    }
    public Ship(String name, int length, Position position, Orientation direction) {
        this.name = name;
        this.length = length;
        this.position = position;
        this.orientation = orientation;
    }
    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String toGraphicLength() {
        return ("" + Board.SHIP).repeat(length);
    }

    public String toString(){
        return name + ";" + length; /*+ ";" + (position.toString()) + ";" + direction;*/
    }


}
