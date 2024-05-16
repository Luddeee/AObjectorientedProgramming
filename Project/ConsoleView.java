package Project;

public class ConsoleView implements Observer {
    @Override
    public void updateGame(int x, int y, int flag){
    }
    private MineSweeper mineSweeper;

    // The `public ConsoleView(MineSweeper mineSweeper)` constructor in the `ConsoleView` class is
    // initializing a `ConsoleView` object with a reference to a `MineSweeper` object. It sets the
    // `mineSweeper` instance variable of the `ConsoleView` class to the provided `mineSweeper` object.
    // Additionally, it registers the `ConsoleView` object as an observer of the `mineSweeper` object
    // by calling the `registerObserver` method on the `mineSweeper` object with `this` (referring to
    // the current `ConsoleView` object) as the argument. This allows the `ConsoleView` object to
    // receive updates from the `mineSweeper` object and react accordingly.
    public ConsoleView(MineSweeper mineSweeper) {
        this.mineSweeper = mineSweeper;
        mineSweeper.registerObserver(this);
    }

    /**
     * The update function in Java prints the current game state by calling the printGrid method.
     */
    @Override
    public void update() {
        System.out.println("Current game state:");
        printGrid();
    }
    

    /**
     * The `printGrid` method iterates through the MineSweeper grid and prints the status of each cell.
     */
    private void printGrid() {
        for (int i = 0; i < mineSweeper.getRow(); i++) {
            for (int j = 0; j < mineSweeper.getRow(); j++) {
                // Assuming the existence of a method in MineSweeper to check the status of each cell
                System.out.print(mineSweeper.getCellStatus(i, j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void updateSounds(String soundType) {
    }
}
