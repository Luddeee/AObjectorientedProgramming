package Project;

/**
 * The `HardStrategy` class implements the `GameStrategy` interface and sets the difficulty of a
 * MineSweeper game to hard with 24 mines and a 99x99 grid.
 */
public class HardStratergy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Hard", 24, 99);
    }
}
