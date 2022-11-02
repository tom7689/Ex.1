import java.util.ArrayList;

public class Ship {
    private final String name;
    private final int length;
    private final char initial;
    public  Position[] Positions;
    public int hits;
    public Ship(String name, int length,char initial) {
        this.name = name;
        this.length = length;
        this.initial = initial;
        this.hits = 0;
        this.Positions = new Position[length];
    }

    public String getName() {
        return name;
    }
    public char getInitial(){
        return initial;
    }

    public int getLength() {
        return length;
    }

    public Position getStartPosition() {
        return Positions[0];
    }
    public Position getEndPosition() {
        return Positions[length-1];
    }

    public void setPosition(Position Start , Position End) {
        Positions[0]=Start;
        Positions[length-1]=End;
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
    public void addPosition(Position p,int index){
        Positions[index]=p;
    }

}