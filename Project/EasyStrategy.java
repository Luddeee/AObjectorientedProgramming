package Project;

/**
 * The EasyStrategy class implements the GameStrategy interface and sets the difficulty of a
 * MineSweeper game to "Easy" with 8 rows and 5 columns.
 */
public class EasyStrategy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Easy", 8, 5);
    }
}
