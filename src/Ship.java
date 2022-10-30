public class Ship {
    private final String name;
    private final int length;
    private Position startPosition;
    private Position endPosition;
    private boolean[] hits;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.startPosition = null;
        this.endPosition = null;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Position getStartPosition() {
        return startPosition;
    }
    public Position getEndPosition() {
        return endPosition;
    }

    public void setPosition(Position Start , Position End) {
        this.startPosition = Start;
        this.endPosition = End;
    }


    public String toString(){
        return name + ";" + length; /*+ ";" + (position.toString()) + ";" + direction;*/
    }


}
