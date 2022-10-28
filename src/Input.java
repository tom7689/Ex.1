
import java.util.Scanner;

public class Input {
    private final Scanner aInput = new Scanner(System.in);

    public Position readPosition(String pLine) {
        while (true) {
            Position aPosition = parsePosition(pLine);

            if (aPosition != null) {
                return aPosition;
            }
            System.out.println("This is not a valid position");
        }
    }
    public Position parsePosition(String pLine) {
        String aLine = pLine.toLowerCase();

        char aLetter = aLine.charAt(0);
        if (aLetter > 'j' || aLetter < 'a')
            System.out.println("Column coordinate is not valid");

        int aColumnIndex = aLetter - 'a';

        char aNumber = aLine.charAt(1);
        if (aNumber > '9' || aNumber < '0')
            System.out.println("Row coordinate is not valid");

        int aRowIndex = aNumber - '1' + 1;

        return new Position(aColumnIndex, aRowIndex);
    }
    public Ship inputShipPosition(Ship pShip) {
        Ship aShip = pShip;
        System.out.println("Place your " + aShip.getName() + " (start and end position, separated by a comma)");
        String aLine = aInput.nextLine();
        if (aLine.length() != 5) {
            System.out.println("Ship coordinates are not valid");
            System.out.println("Try again");

        }
        Position aStartPosition = readPosition(aLine.substring(0,2));

        Position aEndPosition = readPosition(aLine.substring(3));
        if (hasCorrectLength(aShip, aStartPosition, aEndPosition)) {
            aShip.setPosition(aStartPosition);

            return pShip;
        }
        throw new IllegalArgumentException();
    }

    private boolean hasCorrectLength(Ship pShip, Position pStartPosition, Position pEndPosition) {
        return pStartPosition.getaColumnIndex() - pEndPosition.getaColumnIndex() == pShip.getLength() - 1 ||
                pEndPosition.getaColumnIndex() - pStartPosition.getaColumnIndex() == pShip.getLength() - 1 ||
                pStartPosition.getaRowIndex() - pEndPosition.getaRowIndex() == pShip.getLength() - 1 ||
                pEndPosition.getaRowIndex() - pStartPosition.getaRowIndex() == pShip.getLength() - 1;
    }
}

