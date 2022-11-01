import java.util.Random;
import java.util.ArrayList;
public class Player {
    private Fleet fleet;
    public Grid grid;

    public ArrayList<Position> shoots= new ArrayList<>();

    public Player(Fleet fleet,Grid grid){
        this.fleet = fleet;
        this.grid = grid;
    }

    public void place_ship(Ship s,Position p1, Position p2){
        if (p1.getaRowIndex()==p2.getaRowIndex()){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                grid.setPosition(temp,'p');
                s.addPosition(temp);
            }
        }
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());

            for (int i = min; i<=max; i++){
                Position temp = new Position(p1.getaColumnIndex(),i);
                grid.setPosition(temp,'p');
                s.addPosition(temp);
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
            for (int j = 0; j<s.Positions.size();j++){
                Position p1 = s.Positions.get(j);
                if (p.getaColumnIndex()==p1.getaColumnIndex() && p.getaRowIndex()==p1.getaRowIndex()){
                    s.setHit();
                    return s;
                }
            }
        }
        s= new Ship("",0);
        return  s;

    }
    public void com_shoot(Grid EnemyGrid,Fleet EnemyFleet){
        Random rand = new Random();
        int target = rand.nextInt(100);
        Position p= new Position(target/10,target%10);
        while (shoots.contains(p)){
            target = rand.nextInt(100);
            p= new Position(target/10,target%10);
        }
        shoots.add(p);

        if (EnemyGrid.getPosition(p)==' '){
            EnemyGrid.setPosition(p,'o');
        } else{
            Ship s = searchHit(p,EnemyFleet);
            if (s.getLength()==0){
                System.out.print("FEHLER");
            }
            if (s.isDestroyed()){
              for (int i = 0; i<s.getLength();i++){
                  Position p1 = s.Positions.get(i);
                  EnemyGrid.setPosition(p1,'s');
              }
            }
            else{
                EnemyGrid.setPosition(p,'X');
            }
        }

    }

    public void player_shoot(Grid EnemyGrid,Fleet EnemyFleet){
        Input in = new Input();
        Position p=in.enterShot();
        while(shoots.contains(p)) {
            p = in.enterShot();
        }
        shoots.add(p);
        if (EnemyGrid.getPosition(p)==' '){
            EnemyGrid.setPosition(p,'o');
        } else{
            Ship s = searchHit(p,EnemyFleet);
            if (s.isDestroyed()){
                for (int i = 0; i<s.getLength();i++){
                    Position p1 = s.Positions.get(i);
                    EnemyGrid.setPosition(p1,'s');
                }
            }else{
                EnemyGrid.setPosition(p,'X');
                searchHit(p,EnemyFleet);
            }
        }
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