public interface Spieler {
    void place_ship(Ship s,Position p1, Position p2);

    Ship searchHit(Position p, Fleet EnemyFleet);

    void place();
    boolean win();
}
