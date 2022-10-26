
import java.util.Scanner;

public class Input {
    private final Scanner aInput = new Scanner(System.in);

    public Position readPosition() {
        while (true) {
            String aLine = aInput.nextLine();
            Position position = parsePosition(aLine);

            if (position != null) {
                return position;
            }
            System.out.println("This is not a valid position");
        }
    }
    public Position parsePosition(String pLine) {
        String aLine = pLine.trim().toLowerCase();
        if (aLine.length() != 3) {
            throw new IllegalArgumentException();
        }
        char aLetter = aLine.charAt(0);

        if (aLetter > 'j' || aLetter < 'a')
            throw new IllegalArgumentException();
        int aColumnIndex = aLetter - 'a';

        char aNumber = aLine.charAt(2);
        if (aNumber > '9' || aNumber < '0')
            throw new IllegalArgumentException();
        int aRowIndex = aNumber - '1' + 1;
        return new Position(aColumnIndex, aRowIndex);
    }
    public Position inputShipPosition(Ship pShip) {
        System.out.print("Enter Start-Position:");
        Position aStartPosition = readPosition();
        System.out.print("Enter End-Position:");
        Position aEndPosition = readPosition();
        if (hasCorrectLength(pShip, aStartPosition, aEndPosition)) {
            return aStartPosition;
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

