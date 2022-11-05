/**
 * represents a Block in the Grid.
 */
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

    /**
     * Block is set to EMPTY and HIDDEN and its representation to HIDDEN when initialized.
     */
    public Block() {
        aType = type.EMPTY;
        aStatus = status.HIDDEN;
        representation = HIDDEN;
    }

    /**
     * Adjusts the Block when a PlayerShip is assigned to it.
     * @param pShip Ship that the Player sets to the Block.
     */
    public void setPlayerShip(Ship pShip) {
        aShip = pShip;
        aType = type.SHIP;
        initial = aShip.getInitial();
        representation = initial;
    }

    /**
     * Adjusts the Block when a ComputerShip is assigned to it.
     * @param pShip Ship that the Computer sets to the Block.
     */
    public void setComShip(Ship pShip) {
        aShip = pShip;
        aType = type.SHIP;
        initial = aShip.getInitial();
        //representation = initial; //to show the computers ships.
    }

    /**
     * Sets the Block with a Ship to HIT ('X').
     */
    public void setShot() {
        assert aShip != null;
        aStatus = status.HIT;
        representation = HIT;
    }

    /**
     * Sets the Block without a Ship to MISS ('o').
     */
    public void setMiss() {
        assert aShip == null;
        aStatus = status.HIT;
        representation = MISS;
    }

    /**
     * Reveals the Block to show the initial of the ship.
     */
    public void reveal() {
        aStatus = status.REVEALED;
        representation = initial;
    }

    /**
     * @return the Ship that covers the Block.
     */
    public Ship getaShip() {
        return aShip;
    }

    /**
     * @return true when the Block is still empty.
     */
    public boolean isEmpty() {
        return aType == type.EMPTY;
    }

    /**
     * @return true when the Block is covered by a Ship.
     */
    public boolean isShip() {
        return aType == type.SHIP;
    }

    /**
     * @return true when the Block is HIT or MISS.
     */
    public boolean isShot() {
        return aStatus == status.HIT || aStatus == status.REVEALED;
    }

    public String toString() {
        return Character.toString(representation);
    }
}
