import java.util.Scanner;

/**
 * Creates Input objects from the user to build position objects.
 */
public class Input {
    private final Scanner aInput = new Scanner(System.in);

    /**
     *
     * @param pLine A String with two chars.
     * @return A new position object when the column and row inputs are valid.
     */
    private Position parsePosition(String pLine) {
        String aLine = pLine.toLowerCase();
        assert aLine.length() == 2;

        char aLetter = aLine.charAt(0);
        char aNumber = aLine.charAt(1);

        if (aLetter > 'j' || aLetter < 'a' || aNumber > '9' || aNumber < '0') {
            if (aLetter > 'j' || aLetter < 'a') {
                System.out.println("Column coordinate is not valid");
            }
            if (aNumber > '9' || aNumber < '0') {
                System.out.println("Row coordinate is not valid");
            }
        }

        int aColumnIndex = aLetter - 'a';
        int aRowIndex = aNumber - '1' +1;

        return new Position(aColumnIndex, aRowIndex);
    }

    /**
     * Asks the user to input their ships with start and end position and validates the length.
     * @param pShip ship of the Players fleet.
     */
    public void inputShipPosition(Ship pShip) {
        System.out.println("Place your " + pShip.getName() + " of length "+pShip.getLength()+" (start and end position, separated by a comma)");
        while (true) {
            String aLine = aInput.nextLine();
            if (aLine.length() == 5) {
                assert aLine.charAt(2) == ',';
                Position aStartPosition = parsePosition(aLine.substring(0,2));
                Position aEndPosition = parsePosition(aLine.substring(3));
                if (hasCorrectLength(pShip, aStartPosition, aEndPosition)) {
                    pShip.setPosition(aStartPosition, aEndPosition);
                    return;
                }
            }
            System.out.println("Ship coordinates are not valid. Try again");
        }
    }

    /**
     *
     * @param pShip ship of the Players fleet.
     * @param pStartPosition a valid start position from the user.
     * @param pEndPosition a valid end position from the user.
     * @return true when start and end position are valid for the ship.
     */
    private boolean hasCorrectLength(Ship pShip, Position pStartPosition, Position pEndPosition) {
        return Math.abs(pStartPosition.getaColumnIndex() - pEndPosition.getaColumnIndex()) == pShip.getLength() - 1 ||
                Math.abs(pStartPosition.getaRowIndex() - pEndPosition.getaRowIndex()) == pShip.getLength() - 1;
    }

    /**
     * Asks the user to enter a shot to the target grid.
     * @return a valid position on the grid.
     */
    public Position enterShot() {
        System.out.println("Enter shot: ");
        while (true) {
            String aLine = aInput.nextLine();
            if (aLine.length() == 2) {
                return parsePosition(aLine.substring(0, 2));
                }
            System.out.println("Shot coordinates are not valid. Try again");
        }
    }
}
