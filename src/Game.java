import java.util.Random;

public class Game {
    public static void main(String[] args) {
        //Fleet playerFleet = new Fleet(Ship.getPlayerShips());
        //Fleet comFleet = new Fleet(Ship.getComShips());
        //Grid playerGrid = new Grid();
        //Grid comGrid = new Grid();
        Player player = Player.getInstance();
        Computer com = Computer.getInstance();
        com.place();
        Output out = new Output(Computer.getGrid(), Player.getGrid());
        out.print();
        player.place();
        out.print();
        Random rand = new Random();
        int r = rand.nextInt(2);
        if (r == 0) {
            System.out.println("Player begins");
            while (true) {
                player.shoot();
                if (player.win()) {
                    out.print();
                    System.out.println("You win");
                    break;
                }
                com.shoot();
                out.print();
                if (com.win()) {
                    out.print();
                    System.out.println("Computer wins");
                    break;
                }
            }
        }
        if(r==1){
            com.shoot();
            out.print();
            System.out.println("Computer begins");
            while (true) {
                com.shoot();
                if (com.win()) {
                    out.print();
                    System.out.println("Computer wins");
                    break;
                }
                player.shoot();
                out.print();
                if (player.win()) {
                    out.print();
                    System.out.println("You win");
                    break;
                }
            }
        }
    }
}