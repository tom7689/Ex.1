import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Grid {
    private final int length; //one variable for rows = columns = 10 [10x10 matrix]
    private char[][] board;
    private int numShips = 0;
    public static final char HIT = 'X';
    public static final char MISS = 'o';

    public Board(int length){
        this.length = length;
        board = initBoard();
    }

    public Board(char[][] matrix){
        this.length = matrix.length;
        board = matrix;
    }

    private char[][] initBoard(){
        char[][] matrix = new char[length][length];
        for (char[] row: matrix){
            Arrays.fill(row, null);
        }
        return matrix;
    }

    public void reset(){
        numShips = 0;
        board = initBoard();
    }

}
