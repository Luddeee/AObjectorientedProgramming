package Project;

/**
 * The `ProjectStart` class initializes a `MineSweeper` game and sets up the grid, console view, and
 * previous game state.
 */
public class ProjectStart {
    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper();
        new grid(mineSweeper);
        new ConsoleView(mineSweeper);
        new PrevGameState(mineSweeper);
    }
}