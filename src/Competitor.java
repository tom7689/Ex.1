/**
 * Player and Computer implement the Competitor interface.
 */
public interface Competitor {
    void place_ship(Ship s, Position p1, Position p2);
    void place();
    void shoot();
    boolean win();
}
