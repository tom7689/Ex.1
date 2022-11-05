import java.util.HashMap;
import java.util.Map;

public enum ShipType {CARRIER("Carrier",6, 'C', 1), BATTLESHIP("BattleShip",4, 'B', 0),
    SUBMARINE("Submarine",3, 'S', 0), PATROLBOAT("PatrolBoat", 2,'P', 0);

    private static final Map<ShipType, String> SHIP_TYPE_STRING_HASH_MAP = new HashMap<>();
    private static final Map<ShipType, Integer> SHIP_TYPE_LENGTH_HASH_MAP = new HashMap<>();

    static {
        for (ShipType s : values()) {
            SHIP_TYPE_STRING_HASH_MAP.put(s, s.name);
            SHIP_TYPE_LENGTH_HASH_MAP.put(s, s.length);
        }
    }
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

    public static String getStringOfShipType(ShipType pShipType) {
        return SHIP_TYPE_STRING_HASH_MAP.get(pShipType);
    }
    public static Integer getLengthOfShipType(ShipType pShipType) {
        return SHIP_TYPE_LENGTH_HASH_MAP.get(pShipType);
    }
}
