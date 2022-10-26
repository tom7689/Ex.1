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
            Arrays.fill(row, ' ');                                                              // vo null uf ' ' gänderet demit mer grad die leere spaces chönd usgeh
        }
        return matrix;
    }

    public void reset(){
        numShips = 0;
        board = initBoard();
    }


    public char getPosition(Position p) {return board[p.getaRowIndex()][p.getaColumnIndex()];}

    public void setPosition(Position p, char c){board[p.getaRowIndex()][p.getaColumnIndex()] = c;}

    public boolean checkBorder(Position p){
        if (p.getaColumnIndex()>9 || p.getaColumnIndex()<0 || p.getaRowIndex()>9 || p.getaRowIndex()<0){
            return false;
        }
        return true;
    }

    public boolean spot_isfree(Position p1, Position p2){                   //checks if grid position is free to place a ship
        boolean test= true;
        if (p1.getaRowIndex()==p2.getaRowIndex()){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            for (int i = min; i<=max; i++){
                if (board[p1.getaRowIndex()][i]!=' '){
                    test=false;
                }
            }
        }
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());
            for (int i = min; i<=max; i++){
                if (board[i][p1.getaColumnIndex()]!=' '){
                    test=false;
                }
            }
        }
        return test;
    }
}
