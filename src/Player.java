import java.util.Random;
import java.util.ArrayList;
public class Player {
    private Fleet fleet;
    private String name;
    public Grid grid;

    public ArrayList<Integer> shoots= new ArrayList<>();

    public Player(String name,Fleet fleet,Grid grid){
        this.name = name;
        this.fleet = fleet;
        this.grid = grid;
    }
    public String vertical(int row, int col){                        //shows if ship is too large and returns value
        if (row<0){
            return "t";
        }else{
            Position p = new Position(col,row);
            return Grid.get_pos(p);
        }
    }

    public String horizontal(int row, int col){                        //shows if ship is too large and returns value

        if (col>9) {
            return "t";
        }else{
            Position p = new Position(col,row);
            return Grid.get_pos(p);
        }
    }

    public int spot_isfree(Position p, int direction,int ship_len){         //checks if grid position is free to place a ship
        if (Grid.get_pos(p)==" ") {
            int row= p.getaRowIndex();
            int col= p.getaColumnIndex();
            if (direction==0){
                boolean free=true;
                for (int j = ship_len;j>=0;j--){                    //checks if vertical placement is an option
                    if(vertical(row-j,col)!=" "){
                        free=false;
                    }
                }
                if (free){
                    return 0;
                }
            }
            if (direction==1){
                boolean free=true;
                for (int j = ship_len;j>=0;j--){             //checks if horizontal placement is an option
                    if(horizontal(row,col+j)!=" "){
                        free=false;
                    }
                }
                if (free) {
                    return 1;
                }
            }
        }
        return -1;                                              // if the spot isn't available
    }


    public void place_ship(Ship s,Position p, int direction){
        int row = p.getaRowIndex();
        int col= p.getaColumnIndex();
        int len = s.size();

        if (direction==0){
            for (int i = 0; i<len;i++){
                Grid.set_pos(row-1,col,s.get_initial());                 //places ship vertical to the shiptype initial
            }
        }else {
            for (int i = 0; i < len; i++) {
                Grid.set_pos(row , col+i, s.get_initial());                 //places ship horizontal to the shiptype initial
            }
        }
    }

    public void com_place(){                      //places the ships for com
        Random rand = new Random();
        Ship s;
        for ( int i=0; i<fleet.size();i++){     //picks all ships
            s = fleet.get(i);
            boolean u = true;

            while (u){                                              //tries to position at random
                int r = rand.nextInt(100);
                Position p= new Position(r/10,r%10);
                int direction= rand.nextInt(2);
                if (spot_isfree(p,direction,s.size())) {
                    place_ship(s, p, 0);
                    u = false;
                }
            }
        }
    }

    public void com_shoot(){
        Random rand = new Random();
        int target = rand.nextInt(100);
        while (shoots.contains(target)){
            target = rand.nextInt(100);
        }
        shoots.add(target);
        Position p= new Position(target/10,target%10);
        if (Grid.get_pos(p)==" "){
            Grid.set_pos(p,"o");
        } else {
            Ship s;                                 //ship which is hit ???
            if (s.isDestroyed()){

            }else{
                Grid.set_pos(p,"X");
            }
        }

    }

    public void player_shoot(int target){
        if (shoots.contains(target)){
            Output.shoot_fehler();
        }
        else {
            shoots.add(target);
            Position p= new Position(target/10,target%10);
            if (Grid.get_pos(p)==" "){
                Grid.set_pos(p,"o");
            } else {
                Ship s;                                 //ship which is hit ???
                if (s.isDestroyed()){

                }else{
                    Grid.set_pos(p,"X");
                }
            }
        }
    }

    public void player_place(){
        Ship s;
        Input in=new Input();

        for ( int i=0; i<fleet.size();i++) {     //picks all ships
            s = fleet.get(i);
            System.out.println("Give Position for " + s.name() + " of length " + s.size() + ",a direction (0= vertical, 1= horizontal)");
            boolean u = true;
            while (u) {                  //tries to position
                Position p = new Position(in.readPosition());
                int direction = Input.readDirection();
                if (spot_isfree(p, direction, s.size())){
                    place_ship(s, p, 0);
                    u = false;
                } else {
                    System.out.println("This location is Invalid");
                }
            }
        }
    }


}
