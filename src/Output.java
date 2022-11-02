public class Output {

    private final Grid targetGrid;
    private final Grid oceanGrid;
    private final String LETTERS = "  A B C D E F G H I J  ";
    private final String SEPARATOR = " +-+-+-+-+-+-+-+-+-+-+ ";

    public Output(Grid targetGrid, Grid oceanGrid) {
        this.targetGrid = targetGrid;
        this.oceanGrid = oceanGrid;
    }

    public void print() {
        System.out.println("===== TARGET GRID =====");
        printGrid(this.targetGrid);
        System.out.println("=======================\n-----------------------\n");
        System.out.println("===== OCEAN GRID =====");
        printGrid(this.oceanGrid);
    }

    private void printGrid(Grid grid) {
        System.out.println(LETTERS);
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
