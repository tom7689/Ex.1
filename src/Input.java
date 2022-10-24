
import java.util.Scanner;

public class Input {
    private final Scanner aInput = new Scanner(System.in);

    public Position readPosition() {
        System.out.println("Enter Position");
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
}

