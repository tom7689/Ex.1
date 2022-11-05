public class Block {
    private enum type {SHIP, EMPTY}
    private enum status {HIDDEN, HIT, REVEALED}
    private final static char HIDDEN = ' ';
    private final static char HIT = 'X';
    private final static char MISS = 'o';
    private type aType;
    private status aStatus;
    private Ship aShip;
    private char initial;
    private char representation;

    public Block() {
        aType = type.EMPTY;
        aStatus = status.HIDDEN;
        representation = HIDDEN;
    }
    public void setPlayerShip(Ship pShip) {
        aShip = pShip;
        aType = type.SHIP;
        initial = aShip.getInitial();
        representation = initial;
    }
    public void setComShip(Ship pShip) {
        aShip = pShip;
        aType = type.SHIP;
        initial = aShip.getInitial();
        representation = initial;
    }
    public void setShot() {
        aStatus = status.HIT;
        representation = HIT;
    }
    public void setMiss() {
        aStatus = status.HIT;
        representation = MISS;
    }
    public void reveal() {
        aStatus = status.REVEALED;
        representation = initial;
    }
    public Ship getaShip() {
        return aShip;
    }
    public boolean isEmpty() {
        return aType == type.EMPTY;
    }
    public boolean isShip() {
        return aType == type.SHIP;
    }
    public boolean isShot() {
        return aStatus == status.HIT || aStatus == status.REVEALED;
    }
    public String toString() {
        return Character.toString(representation);
    }
}
