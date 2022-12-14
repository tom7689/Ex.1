public class Output {

    private static final Output INSTANCE = new Output();
    private final Grid targetGrid;
    private final Grid oceanGrid;

    private Output() {
        this.targetGrid = Computer.getGrid();
        this.oceanGrid = Player.getGrid();
    }

    public static Output getInstance() {
        return INSTANCE;
    }

    public void print() {
        System.out.println("===== TARGET GRID =====");
        printGrid(this.targetGrid);
        System.out.print("=======================\n-----------------------\n");
        System.out.println("===== OCEAN  GRID =====");
        printGrid(this.oceanGrid);
        System.out.println("=======================");
    }

    private void printGrid(Grid grid) {
        String LETTERS = "  A B C D E F G H I J  ";
        System.out.println(LETTERS);
        String SEPARATOR = " +-+-+-+-+-+-+-+-+-+-+ ";
        System.out.println(SEPARATOR);
        for (int i=0;i<10;i++){
            System.out.print(i);
            for (int j=0;j<10;j++){
                System.out.print("|"+grid.getGrid()[i][j]);

            }
            System.out.println("|"+i);
        }
        System.out.println(SEPARATOR);
        System.out.println(LETTERS);
    }
}
