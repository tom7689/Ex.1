import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Fleet playerFleet = new Fleet(Ship.getPlayerShips());
        Fleet comFleet = new Fleet(Ship.getComShips());
        Grid playerGrid = new Grid();
        Grid comGrid = new Grid();
        Player player = new Player(playerFleet, playerGrid, comGrid);
        Computer com = new Computer(comFleet, comGrid, playerGrid);
        com.place();
        Output out = new Output(comGrid, playerGrid);
        out.print();
        player.place();
        out.print();
        Random rand = new Random();
        int r = rand.nextInt(2);
        if (r == 0) {
            System.out.println("Player begins");
            while (true) {
                player.shoot(com);
                if (player.win()) {
                    System.out.println("You win");
                    break;
                }
                com.shoot(player);
                out.print();
                if (com.win()) {
                    System.out.println("Computer wins");
                    break;
                }
            }
        }
        if(r==1){
            System.out.println("Computer begins");
            while (true) {
                com.shoot(player);
                if (com.win()) {

                    System.out.println("Computer wins");
                    break;
                }
                out.print();
                player.shoot(com);
                if (player.win()) {
                    System.out.println("You win");
                    break;
                }
            }
        }
    }
}