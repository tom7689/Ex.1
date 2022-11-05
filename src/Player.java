public class Player implements Spieler {
    public final Fleet fleet;
    public Grid oceanGrid;
    public Grid targetGrid;


    public Player(Fleet fleet, Grid oceanGrid, Grid targetGrid){
        this.fleet = fleet;
        this.oceanGrid = oceanGrid;
        this.targetGrid = targetGrid;
    }

    public void place_ship(Ship s,Position p1, Position p2){
        if (p1.sameRow(p2)){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                s.addPosition(temp,count);
                count++;
            }
            oceanGrid.setPlayerShip(s);
        }
        if (p1.sameColumn(p2)){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(p1.getaColumnIndex(),i);
                s.addPosition(temp,count);
                count++;
            }
            oceanGrid.setPlayerShip(s);
        }
    }
    public void shoot(Computer Enemy){
        targetGrid = Enemy.oceanGrid;
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

    public void place(){
        Input in = new Input();
        for (int i=0; i<fleet.size();i++) {     //picks all ships
            Ship s = fleet.get(i);
            in.inputShipPosition(s);
            while (true){
                if (oceanGrid.spotIsFree(s.getStartPosition(),s.getEndPosition())){
                    place_ship(s,s.getStartPosition(),s.getEndPosition());
                    Output output = new Output(targetGrid, oceanGrid);
                    output.print();
                    break;
                }else{
                    System.out.println("Spot is already taken, try a new position");
                    in.inputShipPosition(s);
                }
            }
        }
    }
}

