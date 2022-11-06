/**
 * represents the human player playing against the computer. Player holds his fleet and his oceanGrid.
 */
public class Player implements Competitor {
    private final Fleet fleet;
    private static final Grid oceanGrid = new Grid();
    private final Grid targetGrid;

    /**
     *
     * @param fleet all the ships that the standard fleet holds.
     * @param oceanGrid the grid on which the players ships are placed.
     * @param targetGrid the opponents grid that is assigned already at instantiation of the player to print the target
     *                   grid while placing the players ships.
     */
    private static final Player instance = new Player();
    private Player(){
        this.fleet = new Fleet(Ship.getPlayerShips());
        this.targetGrid = Computer.getGrid();
    }
    public static Player getInstance() {
        return instance;
    }
    public static Grid getGrid() {
        return oceanGrid;
    }

    /**
     *
     * @param pShip ship which is placed horizontally or vertically on the ocean grid depending on the input positions.
     * @param pStartPosition the start position of the ship.
     * @param pEndPosition the end position of the ship.
     */
    public void place_ship(Ship pShip, Position pStartPosition, Position pEndPosition){
        if (pStartPosition.sameRow(pEndPosition)){
            int max= Math.max(pStartPosition.getaColumnIndex(),pEndPosition.getaColumnIndex());
            int min= Math.min(pStartPosition.getaColumnIndex(),pEndPosition.getaColumnIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,pStartPosition.getaRowIndex());
                pShip.addPosition(temp,count);
                count++;
            }
            oceanGrid.setPlayerShip(pShip);
        }
        if (pStartPosition.sameColumn(pEndPosition)){
            int max= Math.max(pStartPosition.getaRowIndex(),pEndPosition.getaRowIndex());
            int min= Math.min(pStartPosition.getaRowIndex(),pEndPosition.getaRowIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(pStartPosition.getaColumnIndex(),i);
                pShip.addPosition(temp,count);
                count++;
            }
            oceanGrid.setPlayerShip(pShip);
        }
    }

    /**
     * calls the input object to receive shot coordinates and handles the block representation as well as the ship hits.
     */
    public void shoot(){
        Input in = new Input();
        Position p=in.enterShot();
        while (targetGrid.getBlock(p).isShot()){
            System.out.println("already shoot there");
            p = in.enterShot();
        }
        Block enemyBlock = targetGrid.getBlock(p);
        if (enemyBlock.isEmpty()){
            enemyBlock.setMiss();
        } else if (enemyBlock.isShip()){
            enemyBlock.setShot();
            Ship aShip = enemyBlock.getaShip();
            aShip.setHit();
            if (aShip.isDestroyed()){
                fleet.addSunkShip(aShip);
                for (Position shipPosition : aShip.getPositions()){
                    Block hitShipBlock = targetGrid.getBlock(shipPosition);
                    hitShipBlock.reveal();
                }
            }
        }
    }
    public boolean win(){
        return fleet.size() == fleet.sizeSunk();
    }

    /**
     * calls the input object to receive the coordinates to place the ship on the players ocean grid.
     */
    public void place(){
        Input in = new Input();
        for (int i=0; i<fleet.size();i++) {     //picks all ships
            Ship s = fleet.get(i);
            in.inputShipPosition(s);
            while (true){
                if (oceanGrid.spotIsFree(s.getStartPosition(),s.getEndPosition())){
                    place_ship(s,s.getStartPosition(),s.getEndPosition());
                    Output.getInstance().print();
                    break;
                }else{
                    System.out.println("Spot is already taken, try a new position");
                    in.inputShipPosition(s);
                }
            }
        }
    }
}

