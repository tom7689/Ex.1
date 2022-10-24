
import java.util.HashMap;
import java.util.Map;

public enum ShipType {CARRIER("Carrier",6, 'C'), BATTLESHIP("BattleShip",4, 'B'),
    SUBMARINE("Submarine",3, 'S'), PATROLBOAT("PatrolBoat", 2,'P');

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

    ShipType(String name, int length, char initial) {
        this.name = name;
        this.length = length;
        this.initial = initial;
    }

    public static String getStringOfShipType(ShipType pShipType) {
        return SHIP_TYPE_STRING_HASH_MAP.get(pShipType);
    }
    public static Integer getLengthOfShipType(ShipType pShipType) {
        return SHIP_TYPE_LENGTH_HASH_MAP.get(pShipType);
    }
}
