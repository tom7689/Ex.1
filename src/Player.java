public class Player implements Spieler {
    public final Fleet fleet;
    public Grid grid;
    public Grid targetGrid;

    private final boolean[][] Shoots;

    public Player(Fleet fleet,Grid grid, Grid target){
        this.fleet = fleet;
        this.grid = grid;
        this.targetGrid = target;
        this.Shoots = new boolean[10][10];
    }

    public void place_ship(Ship s,Position p1, Position p2){
        if (p1.getaRowIndex()==p2.getaRowIndex()){
            int max= Math.max(p1.getaColumnIndex(),p2.getaColumnIndex());
            int min= Math.min(p1.getaColumnIndex(),p2.getaColumnIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(i,p1.getaRowIndex());
                grid.setPosition(temp,s.getInitial());
                s.addPosition(temp,count);
                count++;
            }
        }
        if (p1.getaColumnIndex()==p2.getaColumnIndex()){
            int max= Math.max(p1.getaRowIndex(),p2.getaRowIndex());
            int min= Math.min(p1.getaRowIndex(),p2.getaRowIndex());
            int count=0;
            for (int i = min; i<=max; i++){
                Position temp = new Position(p1.getaColumnIndex(),i);
                grid.setPosition(temp,s.getInitial());
                s.addPosition(temp,count);
                count++;
            }
        }
    }


    public Ship searchHit(Position p,Fleet EnemyFleet){
        Ship s;
        for (int i =0 ; i < fleet.size();i++){
            s = EnemyFleet.get(i);
            for (int j = 0; j<s.Positions.length;j++){
                Position p1 = s.Positions[j];
                if (p.equals(p1)){
                    s.setHit();
                    return s;
                }
            }
        }
        //s= new Ship("",0,'L');
        return null;
    }

    public void shoot(Computer Enemy){
        Input in = new Input();
        Position p=in.enterShot();
        while (Shoots[p.getaRowIndex()][p.getaColumnIndex()]){
            System.out.println("already shoot there");
            p = in.enterShot();
        }
        Shoots[p.getaRowIndex()][p.getaColumnIndex()]=true;
        if (Enemy.grid.getPosition(p)==' '){
            Enemy.grid.setPosition(p,Grid.MISS);
            targetGrid.setPosition(p, Grid.MISS);
        } else{
            Ship s = searchHit(p,Enemy.fleet);
            if (s.isDestroyed()){
                fleet.addSunkShip(s);
                for (int i = 0; i<s.getLength();i++){
                    Position p1 = s.Positions[i];
                    Enemy.grid.setPosition(p1,s.getInitial());
                    targetGrid.setPosition(p1, s.getInitial());
                }
            }
            else{
                Enemy.grid.setPosition(p,Grid.HIT);
                targetGrid.setPosition(p, Grid.HIT);
            }
        }
    }
    public boolean win(){
        return fleet.size() == fleet.sizeSunk();
    }

    public void place(){
        Input in = new Input();
        for ( int i=0; i<fleet.size();i++) {     //picks all ships
            Ship s = fleet.get(i);
            in.inputShipPosition(s);
            while (true){
                if (grid.spot_isfree(s.getStartPosition(),s.getEndPosition())){
                    place_ship(s,s.getStartPosition(),s.getEndPosition());
                    break;
                }else{
                    System.out.println("Spot is already taken, try a new position");
                    in.inputShipPosition(s);
                }
            }
        }
    }
}

