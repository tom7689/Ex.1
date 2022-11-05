public class Grid {
    private final Block[][] board;

    public Grid(){
        board = initBoard();
    }
    public Block[][] getGrid(){
        return board;
    }

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
    public Block getBlock(Position pPosition) {
        return board[pPosition.getaRowIndex()][pPosition.getaColumnIndex()];
    }

    public boolean checkBorder(Position p){
        return p.getaColumnIndex() <= 9 && p.getaColumnIndex() >= 0 && p.getaRowIndex() <= 9 && p.getaRowIndex() >= 0;
    }

    public boolean spotIsFree(Position p1, Position p2){                   //checks if grid position is free to place a shi
        boolean test= true;
        if (p1.sameRow(p2)){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            for (int i = min; i<=max; i++){
                Block aBlock = board[p1.getaRowIndex()][i];
                if (aBlock.isShip()){
                    test=false;
                    break;
                }
            }
        }
        if (p1.sameColumn(p2)){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());
            for (int i = min; i<=max; i++){
                Block aBlock = board[i][p1.getaColumnIndex()];
                if (aBlock.isShip()){
                    test=false;
                    break;
                }
            }
        }
        return test;
    }
}