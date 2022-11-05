import java.util.Objects;

/**
 * Creating position objects. A position consists of first a column and second a row index,
 * as the input is created as well in this order. Instances of this class are immutable.
 */
public final class Position {
    private final int aColumnIndex;
    private final int aRowIndex;

    /**
     *
     * @param pColumn the column index of the grid from 0 to 9.
     * @param pRow the row index of the grid from 0 to 9.
     */
    public Position(int pColumn, int pRow) {
        assert 0 <= pRow && pRow <= 9 && 0 <= pColumn && pColumn <= 9;
        aColumnIndex = pColumn;
        aRowIndex = pRow;
    }

    public int getaColumnIndex() {
        return aColumnIndex;
    }
    public int getaRowIndex() {
        return aRowIndex;
    }
    /**
     *
     * @return true when pPosition in the same column.
     */
    public boolean sameColumn(Position pPosition) {
        return aColumnIndex == pPosition.getaColumnIndex();
    }
    /**
     *
     * @return true when pPosition in the same row.
     */
    public boolean sameRow(Position pPosition) {
        return aRowIndex == pPosition.getaRowIndex();
    }
    /**
     *
     * @return true when both the column and row index are the same.
     */
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
