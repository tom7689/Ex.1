import java.util.ArrayList;

public class Ship {

    private static final ArrayList<Ship> PLAYER_SHIPS = new ArrayList<>(10);
    private static final ArrayList<Ship> COM_SHIPS = new ArrayList<>(10);

    //flyweight objects
    static
    {
        for (ShipType aType : ShipType.values()) {
            if (aType.quantity > 1) {
                for (int i=1; i<=aType.quantity; i++) {
                    String aShipName = aType.name + " " + i;
                    PLAYER_SHIPS.add(new Ship(aType, aShipName));
                    COM_SHIPS.add(new Ship(aType, aShipName));
                }
            }
            else {
                String aShipName = aType.name;
                PLAYER_SHIPS.add(new Ship(aType, aShipName));
                COM_SHIPS.add(new Ship(aType, aShipName));
            }
        }
    }
    private final String name;
    private final int length;
    private final char initial;
    public  Position[] Positions;
    public int hits;


    private Ship(ShipType type, String name) {
        this.name = name;
        this.length = type.length;
        this.initial = type.initial;
        this.hits = 0;
        this.Positions = new Position[length];

    }
    public static ArrayList<Ship> getPlayerShips() {
        return PLAYER_SHIPS;
    }
    public static ArrayList<Ship> getComShips() {
        return COM_SHIPS;
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