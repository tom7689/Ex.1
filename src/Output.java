public class Output {

    private Grid targetGrid;
    private Grid oceanGrid;
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
        for (int i = 0; i < 10; i++) {
            System.out.println(); //What values do I get from the grid?
        }
        System.out.println(SEPARATOR);
        System.out.println(LETTERS);
    }
}
