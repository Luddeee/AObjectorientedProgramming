package Project;

public class ProjectStart {
    public static void main(String[] args) {
        MineSweeper mineSweeper = new MineSweeper();
        new grid(mineSweeper);
        new ConsoleView(mineSweeper);
        new PrevGameState(mineSweeper);
    }
}