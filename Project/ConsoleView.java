package Project;

public class ConsoleView implements Observer {
    private MineSweeper mineSweeper;

    public ConsoleView(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        mineSweeper.registerObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Current game state:");
        printGrid();
    }

    private void printGrid() {
        for (int i = 0; i < mineSweeper.getRow(); i++) {
            for (int j = 0; j < mineSweeper.getRow(); j++) {
                // Assuming the existence of a method in MineSweeper to check the status of each cell
                System.out.print(mineSweeper.getCellStatus(i, j) + " ");
            }
            System.out.println();
        }
    }
}
