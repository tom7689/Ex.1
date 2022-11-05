import java.util.ArrayList;
import java.util.Iterator;

/**
 * holds the ships of the standard fleet.
 */
public class Fleet implements Iterable<Ship> {

    private final ArrayList<Ship> aShips;
    private final ArrayList<Ship> sunkShips = new ArrayList<>();

    /**
     *
     * @param pShips the flyweight ship objects.
     */
    public Fleet(ArrayList<Ship> pShips) {
        aShips = pShips;
    }

    public int size() {
        return aShips.size();
    }

    /**
     *
     * @return size of sunk ships to compare with the size of a players fleet.
     */
    public int sizeSunk(){ return sunkShips.size();}

    /**
     *
     * @return each ship one by one.
     */
    public Ship get(int i){return aShips.get(i);}
    public void addSunkShip(Ship ship){
        sunkShips.add(ship);
    }
    @Override
    public Iterator<Ship> iterator() {
        return aShips.iterator();
    }
}
