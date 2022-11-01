
/**
 * Creating position objects. A position consists of first a column and a second row index,
 * as the input is created as well in this order. Instances of this class are immutable.
 */
public final class Position {

    private final int aColumnIndex;
    private final int aRowIndex;


    public Position(int pColumn, int pRow) {
        assert pRow >= 0 && pColumn >= 0;
        aColumnIndex = pColumn;
        aRowIndex = pRow;
    }

    public int getaColumnIndex() {
        return aColumnIndex;
    }

    public int getaRowIndex() {
        return aRowIndex;
    }

    @Override
    public String toString() {
        return "Position: " + aColumnIndex + "," + aRowIndex;
    }
}
