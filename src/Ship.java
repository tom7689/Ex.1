import java.util.ArrayList;

/**
 * Ships that are created as flyweight objects to ensure that both players get the same amount of ships.
 */
public class Ship {

    private static final ArrayList<Ship> PLAYER_SHIPS = new ArrayList<>(10);
    private static final ArrayList<Ship> COM_SHIPS = new ArrayList<>(10);

    // flyweight objects
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
            else if (aType.quantity == 1) {
                String aShipName = aType.name;
                PLAYER_SHIPS.add(new Ship(aType, aShipName));
                COM_SHIPS.add(new Ship(aType, aShipName));
            }
        }
    }
    private final String name;
    private final int length;
    private final char initial;
    private final Position[] positions;
    private int hits;


    private Ship(ShipType type, String name) {
        this.name = name;
        this.length = type.length;
        this.initial = type.initial;
        this.hits = 0;
        this.positions = new Position[length];

    }

    /**
     *
     * @return all the ships received by the players fleet.
     */
    public static ArrayList<Ship> getPlayerShips() {
        return PLAYER_SHIPS;
    }

    /**
     *
     * @return all the ships received by the computers fleet.
     */
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
        return positions[0];
    }
    public Position getEndPosition() {
        return positions[length-1];
    }

    /**
     *
     * @return all positions of the ship.
     */
    public Position[] getPositions() {
        return positions;
    }

    /**
     *
     * @param pStartPosition the start position of the ship already validated from the user.
     * @param pEndPosition the end position of the ship already validated from the user.
     */
    public void setPosition(Position pStartPosition, Position pEndPosition) {
        positions[0]=pStartPosition;
        positions[length-1]=pEndPosition;
    }

    /**
     * increases the hits a ship receives.
     */
    public void setHit(){
        hits++;
    }

    /**
     *
     * @return true when all positions a hit.
     */
    public boolean isDestroyed(){
        return hits == length;
    }

    /**
     *
     * @param pPosition position to add to the ship positions array.
     * @param index to which index of the ship positions array to add the position.
     */
    public void addPosition(Position pPosition, int index){
        positions[index]=pPosition;
    }

    public String toString(){
        return name + "; " + length; /*+ ";" + (position.toString()) + ";" + direction;*/
    }

}