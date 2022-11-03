import java.util.Objects;

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
    public boolean equals(Object pPosition) {
        if (this == pPosition) return true;
        if (pPosition == null || getClass() != pPosition.getClass()) return false;
        Position position = (Position) pPosition;
        return aColumnIndex == position.aColumnIndex && aRowIndex == position.aRowIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aColumnIndex, aRowIndex);
    }

    @Override
    public String toString() {
        return "Position: " + aColumnIndex + aRowIndex;
    }
}
