import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Fleet f1 = new Fleet(Ship.getPlayerShips());
        Fleet f2 = new Fleet(Ship.getComShips());
        Grid g1 = new Grid(10);
        Grid g2 = new Grid(10);
        Grid t1 = new Grid(10);
        Grid t2 = new Grid(10);
        Player player = new Player(f1, g1, t1);
        Computer com = new Computer(f2, g2, t2);
        com.place();
        Output out = new Output(t1, g1);
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
                player.shoot(com);
                out.print();
                if (player.win()) {
                    System.out.println("You win");
                    break;
                }

            }
        }
    }
}