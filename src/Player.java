import java.util.Random;
import java.util.ArrayList;
public class Player {
    private Fleet fleet;
    private String name;
    public Grid grid;

    public ArrayList<Position> shoots= new ArrayList<>();

    public Player(String name,Fleet fleet,Grid grid){
        this.name = name;
        this.fleet = fleet;
        this.grid = grid;
    }

    public void place_ship(Ship s,Position p1, Position p2){
        if (p1.getaRowIndex()==p2.getaRowIndex()){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                grid.setPosition(temp,'s');
            }
        }
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());

            for (int i = min; i<=max; i++){
                Position temp = new Position(p1.getaColumnIndex(),i);
                grid.setPosition(temp,'s');
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

    public void com_shoot(){
        Random rand = new Random();
        int target = rand.nextInt(100);
        Position p= new Position(target/10,target%10);
        while (shoots.contains(p)){
            target = rand.nextInt(100);
            p= new Position(target/10,target%10);
        }
        shoots.add(p);

        if (grid.getPosition(p)==' '){
            grid.setPosition(p,'o');
        } else{
            Ship s;                                 //ship which is hit ???
            if (s.isDestroyed()){

            }else{
                grid.setPosition(p,'X');
            }
        }

    }

    public void player_shoot(){
        System.out.println("Input new shoot (Pos1):");
        Input in = new Input();
        Position p=in.readPosition();
        while(shoots.contains(p)) {
            System.out.println("You shot there already, new shoot position please:");
            p = in.readPosition();
        }

        shoots.add(p);
        if (grid.getPosition(p)==' '){
            grid.setPosition(p,'o');
        } else{
            Ship s;                                 //ship which is hit ???
            if (s.isDestroyed()){

            }else{
                grid.setPosition(p,'X');
            }
        }
    }

    public boolean checkLength(Position p1,Position p2, Ship s){
        int d;
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            d=Math.abs(p1.getaRowIndex()-p2.getaRowIndex());
        }else{
            d=Math.abs(p1.getaColumnIndex()-p2.getaColumnIndex() );
        }
        if (d==s.getLength()-1){
            return true;
        }
        return false;
    }
    public void player_place(){
        Input in = new Input();
        for ( int i=0; i<fleet.size();i++) {     //picks all ships
            Ship s = fleet.get(i);
            while(true) {
                System.out.println("Input startposition of " + s.getName() + " of length " + s.getLength() +"(Pos1):");
                Position p1 =in.readPosition();
                System.out.println("Input endposition (Pos2):");
                Position p2 = in.readPosition();//endpunkt Input?
                if (checkLength(p1,p2,s)) {
                    if (grid.checkBorder(p1) && grid.checkBorder(p2)) {
                        if (grid.spot_isfree(p1, p2)) {
                            place_ship(s, p1, p2);
                            break;
                        } else {
                            System.out.println("Spot is taken new positions please");
                        }
                    } else {
                        System.out.println("Invalid positions new positions please");
                    }
                }else{
                    System.out.println("Invalid length new Position please");
                }
            }
        }
    }


}
