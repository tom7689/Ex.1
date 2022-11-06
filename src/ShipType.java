/**
 * defines the different ship types as enums.
 */
public enum ShipType {CARRIER("Carrier",6, 'C', 1), BATTLESHIP("BattleShip",4, 'B', 0),
    SUBMARINE("Submarine",3, 'S', 0), PATROLBOAT("PatrolBoat", 2,'P', 0);

    public final String name;
    public final int length;
    public final char initial;
    public final int quantity;

    ShipType(String name, int length, char initial, int amount) {
        this.name = name;
        this.length = length;
        this.initial = initial;
        this.quantity = amount;
    }
}
