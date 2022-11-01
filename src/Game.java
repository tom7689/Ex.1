public class Game {
    public static void main(String[] args) {
        Fleet f1= new Fleet(0,0,0,1);
        Fleet f2= new Fleet(0,0,0,1);
        Grid g1 = new Grid(10); Grid g2= new Grid(10);
        Player player= new Player(f1,g1 ); Player com = new Player(f2,g2);
        com.com_place();
        Output out = new Output(g2,g1);
        out.print();
        player.player_place();
        player.player_shoot(com);
        out.print();
        player.player_shoot(com);
        out.print();

    }
}