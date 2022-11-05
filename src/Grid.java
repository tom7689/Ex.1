/**
 * represents the playing grid which consists of blocks.
 */
public class Grid {
    private final Block[][] board;

    public Grid(){
        board = initBoard();
    }
    public Block[][] getGrid(){
        return board;
    }

    /**
     * creates a board of blocks when instantiated.
     */
    private Block[][] initBoard(){
        //one variable for rows = columns = 10 [10x10 matrix]
        int length = 10;
        Block[][] board = new Block[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                board[i][j] = new Block();
            }
        }
        return board;
    }

    /**
     * different methods for placing the players ships and the computers ships as their representation on the board is
     * different.
     */
    public void setPlayerShip(Ship pShip) {
        for (Position shipPosition : pShip.getPositions()) {
            Block block = board[shipPosition.getaRowIndex()][shipPosition.getaColumnIndex()];
            block.setPlayerShip(pShip);
        }
    }
    public void setComShip(Ship pShip) {
        for (Position shipPosition : pShip.getPositions()) {
            Block block = board[shipPosition.getaRowIndex()][shipPosition.getaColumnIndex()];
            block.setComShip(pShip);
        }
    }

    /**
     *
     * @return the block at the respective position
     */
    public Block getBlock(Position pPosition) {
        return board[pPosition.getaRowIndex()][pPosition.getaColumnIndex()];
    }

    public boolean checkBorder(Position p){
        return p.getaColumnIndex() <= 9 && p.getaColumnIndex() >= 0 && p.getaRowIndex() <= 9 && p.getaRowIndex() >= 0;
    }

    /**
     *
     * @return true if the spot for the ship placement is available.
     */
    public boolean spotIsFree(Position pStartPosition, Position pEndPosition){                   //checks if grid position is free to place a shi
        boolean test= true;
        if (pStartPosition.sameRow(pEndPosition)){
            int max= Math.max(pStartPosition.getaColumnIndex(),pEndPosition.getaColumnIndex());
            int min= Math.min(pStartPosition.getaColumnIndex(),pEndPosition.getaColumnIndex());
            for (int i = min; i<=max; i++){
                Block aBlock = board[pStartPosition.getaRowIndex()][i];
                if (aBlock.isShip()){
                    test=false;
                    break;
                }
            }
        }
        if (pStartPosition.sameColumn(pEndPosition)){
            int max= Math.max(pStartPosition.getaRowIndex(),pEndPosition.getaRowIndex());
            int min= Math.min(pStartPosition.getaRowIndex(),pEndPosition.getaRowIndex());
            for (int i = min; i<=max; i++){
                Block aBlock = board[i][pStartPosition.getaColumnIndex()];
                if (aBlock.isShip()){
                    test=false;
                    break;
                }
            }
        }
        return test;
    }
}