import java.util.ArrayList;
import java.util.Iterator;

public class Fleet implements Iterable<Ship> {

    private ArrayList<Ship> aShips = new ArrayList<>();
    private ArrayList<Ship> sunkShips = new ArrayList<>();

    public Fleet(int carrier, int battleship, int submarine, int patrolboat) {

        for (int i = 0; i < carrier; i++) {
            aShips.add(new Ship("Carrier", 6));
        }

        for (int i = 0; i < battleship; i++) {
            aShips.add(new Ship("BattleShip", 4));
        }

        for (int i = 0; i < submarine; i++) {
            aShips.add(new Ship("Submarine", 3));
        }

        for (int i = 0; i < patrolboat; i++) {
            aShips.add(new Ship("PatrolBoat", 2));
        }
    }

    public int size() {
        return aShips.size();
    }

    @Override
    public Iterator<Ship> iterator() {
        return aShips.iterator();
    }
}
