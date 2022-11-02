import java.util.Random;
import java.util.ArrayList;
public class Player {
    private final Fleet fleet;
    public Grid grid;

    private boolean[][] Shoots;

    public Player(Fleet fleet,Grid grid){
        this.fleet = fleet;
        this.grid = grid;
        this.Shoots = new boolean[10][10];
    }

    public void place_ship(Ship s,Position p1, Position p2){
        if (p1.getaRowIndex()==p2.getaRowIndex()){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                grid.setPosition(temp,s.getInitial());
                s.addPosition(temp,count);
                count++;
            }
        }
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(p1.getaColumnIndex(),i);
                grid.setPosition(temp,s.getInitial());
                s.addPosition(temp,count);
                count++;
            }
        }
    }

    public void com_place() {                      //places the ships for com
        Random rand = new Random();
        for (int i = 0; i < fleet.size(); i++) {     //picks all ships
            Ship s = fleet.get(i);
            while (true) {                                              //tries to position at random
                int r = rand.nextInt(100);
                Position p1 = new Position(r / 10, r % 10);
                int direction = rand.nextInt(2);
                if (direction == 0) {
                    Position p2 = new Position(p1.getaColumnIndex(), p1.getaRowIndex() - (s.getLength()-1));
                    if (grid.checkBorder(p2) &&grid.spot_isfree(p1, p2) ) {
                        place_ship(s, p1, p2);
                        break;
                    }
                } else {
                    Position p2 = new Position(p1.getaColumnIndex() + (s.getLength()-1), p1.getaRowIndex());
                    if (grid.checkBorder(p2)  &&grid.spot_isfree(p1, p2) ) {
                        place_ship(s, p1, p2);
                        break;
                    }
                }
            }
        }
    }
    private Ship searchHit(Position p,Fleet EnemyFleet){
        Ship s;
        for (int i =0 ; i < fleet.size();i++){
            s = EnemyFleet.get(i);
            for (int j = 0; j<s.Positions.length;j++){
                Position p1 = s.Positions[j];
                if (p.getaColumnIndex()==p1.getaColumnIndex() && p.getaRowIndex()==p1.getaRowIndex()){
                    s.setHit();
                    return s;
                }
            }
        }
        s= new Ship("",0,'L');
        return  s;

    }
    public void com_shoot(Player Enemy){
        Random rand = new Random();
        int target = rand.nextInt(100);
        Position p= new Position(target/10,target%10);
        while (Shoots[p.getaRowIndex()][p.getaColumnIndex()]){
            target = rand.nextInt(100);
            p= new Position(target/10,target%10);
        }
        Shoots[p.getaRowIndex()][p.getaColumnIndex()]=true;
        if (Enemy.grid.getPosition(p)==' '){
            Enemy.grid.setPosition(p,Grid.MISS);
        } else{
            Ship s = searchHit(p,Enemy.fleet);
            if (s.isDestroyed()){
                fleet.addSunkShip(s);
                for (int i = 0; i<s.getLength();i++){
                    Position p1 = s.Positions[i];
                    Enemy.grid.setPosition(p1,s.getInitial());
                }
            }else{
                Enemy.grid.setPosition(p,Grid.HIT);
            }
        }

    }

    public void player_shoot(Player Enemy){
        Input in = new Input();
        Position p=in.enterShot();
        int target= p.getaColumnIndex()*10 + p.getaRowIndex();
        while (Shoots[p.getaRowIndex()][p.getaColumnIndex()]){
            System.out.println("already shoot there");
            p = in.enterShot();
        }
        Shoots[p.getaRowIndex()][p.getaColumnIndex()]=true;
        if (Enemy.grid.getPosition(p)==' '){
            Enemy.grid.setPosition(p,Grid.MISS);
        } else{
            Ship s = searchHit(p,Enemy.fleet);
            if (s.isDestroyed()){
                fleet.addSunkShip(s);
                for (int i = 0; i<s.getLength();i++){
                    Position p1 = s.Positions[i];
                    Enemy.grid.setPosition(p1,s.getInitial());
                }
            }
            else{
                Enemy.grid.setPosition(p,Grid.HIT);
            }
        }
    }
    public boolean win(){
        return fleet.size() == fleet.sizeSunk();
    }

    public void player_place(){
        Input in = new Input();
        for ( int i=0; i<fleet.size();i++) {     //picks all ships
            Ship s = fleet.get(i);
            in.inputShipPosition(s);
            while (true){
                if (grid.spot_isfree(s.getStartPosition(),s.getEndPosition())){
                    place_ship(s,s.getStartPosition(),s.getEndPosition());
                    break;
                }else{
                    System.out.println("Spot is already taken, try a new position");
                    in.inputShipPosition(s);
                }
            }
        }
    }
}