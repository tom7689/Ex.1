import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Fleet f1 = new Fleet(0, 0, 0, 1);
        Fleet f2 = new Fleet(0, 0, 0, 1);
        Grid g1 = new Grid(10);
        Grid g2 = new Grid(10);
        Player player = new Player(f1, g1);
        Player com = new Player(f2, g2);
        com.com_place();
        Output out = new Output(g2, g1);
        out.print();
        player.player_place();
        out.print();
        Random rand = new Random();
        int r = rand.nextInt(2);
        if (r == 0) {
            System.out.println("Player begins");
            while (true) {
                player.player_shoot(com);
                if (player.win()) {
                    System.out.println("You win");
                    break;
                }
                com.com_shoot(player);
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
                com.com_shoot(player);
                if (com.win()) {
                    System.out.println("Computer wins");
                    break;
                }
                player.player_shoot(com);
                out.print();
                if (player.win()) {
                    System.out.println("You win");
                    break;
                }

            }
        }
    }
}// win nachricht, com board nicht anzeichen