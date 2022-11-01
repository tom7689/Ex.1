import java.util.ArrayList;

public class Ship {
    private final String name;
    private final int length;
    public boolean Destroyed;
    public  ArrayList<Position> Positions;
    public int hits;
    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.hits=0;
        this.Positions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Position getStartPosition() {
        return Positions.get(0);
    }
    public Position getEndPosition() {
        return Positions.get(length-1);
    }

    public void setPosition(Position Start , Position End) {
        this.Positions.set(0,Start);
        this.Positions.set(length-1,End);
    }
    public void setHit(){
        hits++;
    }
    public boolean isDestroyed(){
        if (hits == length){
            return true;
        }
        return false;
    }

    public String toString(){
        return name + ";" + length; /*+ ";" + (position.toString()) + ";" + direction;*/
    }
    public void addPosition(Position p){
        Positions.add(p);
    }

}