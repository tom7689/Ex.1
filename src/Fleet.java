import java.util.ArrayList;
import java.util.Iterator;

public class Fleet implements Iterable<Ship> {

    private ArrayList<Ship> aShips = new ArrayList<>();
    private ArrayList<Ship> sunkShips = new ArrayList<>();

    public Fleet(int carrier, int battleship, int submarine, int patrolboat) {

        for (int i = 0; i < carrier; i++) {
            aShips.add(new Ship(ShipType.CARRIER.name,ShipType.CARRIER.length,ShipType.CARRIER.initial));
        }

        for (int i = 0; i < battleship; i++) {
            aShips.add(new Ship(ShipType.BATTLESHIP.name,ShipType.BATTLESHIP.length,ShipType.BATTLESHIP.initial));
        }

        for (int i = 0; i < submarine; i++) {
            aShips.add(new Ship(ShipType.SUBMARINE.name,ShipType.SUBMARINE.length,ShipType.SUBMARINE.initial));
        }

        for (int i = 0; i < patrolboat; i++) {
            aShips.add(new Ship(ShipType.PATROLBOAT.name,ShipType.PATROLBOAT.length,ShipType.PATROLBOAT.initial));
        }
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
