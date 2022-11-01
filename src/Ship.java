import java.util.ArrayList;

public class Ship {
    private final String name;
    private final int length;
    private final char initial;
    public  ArrayList<Position> Positions;
    public int hits;
    public Ship(String name, int length,char initial) {
        this.name = name;
        this.length = length;
        this.initial = initial;
        this.hits=0;
        this.Positions = new ArrayList<>();
        fillPositions();
    }
    private void fillPositions(){
        for (int i = 0;i < length;i++){
            Position p=new Position(0,0);
            Positions.add(p);
        }
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