public class Game {
    public static void main(String[] args) {
        Fleet f1= new Fleet(1,1,1,1);
        Fleet f2= new Fleet(1,1,1,1);
        Grid g1 = new Grid(10); Grid g2= new Grid(10);
        Player player= new Player(f1,g1 ); Player com = new Player(f2,g2);
        com.com_place();
        Output out = new Output(g2,g1);
        out.print();
        player.player_shoot(g2,f2);
        out.print();
        player.player_shoot(g2,f2);
        out.print();

    }
}