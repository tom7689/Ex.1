import java.util.ArrayList;
import java.util.Iterator;

public class Fleet implements Iterable<Ship> {

    private final ArrayList<Ship> aShips;
    private final ArrayList<Ship> sunkShips = new ArrayList<>();

    public Fleet(ArrayList<Ship> pShips) {
        aShips = pShips;
    }

    public int size() {
        return aShips.size();
    }
    public int sizeSunk(){ return sunkShips.size();}
    public Ship get(int i){return aShips.get(i);}
    public void addSunkShip(Ship ship){
        sunkShips.add(ship);
    }
    @Override
    public Iterator<Ship> iterator() {
        return aShips.iterator();
    }
}
