import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {
    public static void main(String[] args) {
        Player player = Player.getInstance();
        Computer com = Computer.getInstance();
        LinkedList<Competitor> players = new LinkedList<>();
        com.place();
        Output out = Output.getInstance();
        out.print();
        player.place();
        out.print();
        Random rand = new Random();
        int r = rand.nextInt(2);

        if (r == 0) {
            players.addFirst(player);
            players.addLast(com);
            System.out.println("Player begins");
        } else {
            players.addFirst(com);
            players.addLast(player);
            System.out.println("Computer begins");
        }

        while (!player.win() && !com.win()) {
            Competitor s = players.removeFirst();
            s.shoot();
            out.print();
            players.addLast(s);
        }

        if (player.win()) {
            System.out.println("You win");
        } else {
            System.out.println("Computer wins");
        }


        /*if (r == 0) {
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
        }*/
    }
}