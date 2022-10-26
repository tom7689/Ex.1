public enum Orientation {
    RIGHT,
    LEFT,
    UP,
    DOWN;

    public static Orientation decode(char c)  {
        if (c == 'R' || c == 'r') return RIGHT;
        else if (c == 'L' || c == 'l') return LEFT;
        else if (c == 'U' || c == 'u') return UP;
        else if (c == 'D' || c == 'd') return Down;

        else System.out.println("The character '"+c+"' is unable to assign a direction. Use r for Right, l for left, u for up and d for down");
    }


}