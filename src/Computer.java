import java.util.Random;

public class Computer implements Spieler {
    public final Fleet fleet;
    public Grid oceanGrid;
    public Grid targetGrid;

    public Computer(Fleet fleet, Grid oceanGrid, Grid targetGrid){
        this.fleet = fleet;
        this.oceanGrid = oceanGrid;
        this.targetGrid = targetGrid;
    }

    public void place_ship(Ship s, Position p1, Position p2){
        if (p1.sameRow(p2)){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                s.addPosition(temp,count);
                count++;
            }
            oceanGrid.setComShip(s);
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
            oceanGrid.setComShip(s);
        }
    }

    public void place() {                      //places the ships for com
        Random rand = new Random();
        for (int i = 0; i < fleet.size(); i++) {     //picks all ships
            Ship s = fleet.get(i);
            while (true) {                                              //tries to position at random
                int r = rand.nextInt(100);
                Position p1 = new Position(r / 10, r % 10);
                int direction = rand.nextInt(2);
                Position p2;
                if (direction == 0) {
                    p2 = new Position(p1.getaColumnIndex(), p1.getaRowIndex() - (s.getLength() - 1));
                } else {
                    p2 = new Position(p1.getaColumnIndex() + (s.getLength() - 1), p1.getaRowIndex());
                }
                if (oceanGrid.checkBorder(p2) && oceanGrid.spotIsFree(p1, p2) ) {
                    place_ship(s, p1, p2);
                    break;
                }
            }
        }
    }


    public void shoot(Player Enemy){
        targetGrid = Enemy.oceanGrid;
        Random rand = new Random();
        int target = rand.nextInt(100);
        Position p= new Position(target/10,target%10);
        while (targetGrid.getBlock(p).isShot()){
            target = rand.nextInt(100);
            p= new Position(target/10,target%10);
        }
        Block enemyBlock = targetGrid.getBlock(p);
        if (enemyBlock.isEmpty()){
            enemyBlock.setMiss();
        } else if (enemyBlock.isShip()){
            enemyBlock.setShot();
            Ship aShip = enemyBlock.getaShip();
            aShip.setHit();
            if (aShip.isDestroyed()) {
                fleet.addSunkShip(aShip);
                for (Position shipPosition : aShip.getPositions()) {
                    Block hitShipBlock = targetGrid.getBlock(shipPosition);
                    hitShipBlock.reveal();
                }
                if (win()) {
                    for (Ship ship : fleet) {
                        for (Position shipPosition : ship.getPositions()) {
                            Block notHitShipBlock = targetGrid.getBlock(shipPosition);
                            notHitShipBlock.reveal();
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean win(){
        return fleet.size() == fleet.sizeSunk();
    }
}
