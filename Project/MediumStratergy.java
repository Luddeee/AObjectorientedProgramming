package Project;

/**
 * The `MediumStrategy` class implements the `GameStrategy` interface and applies medium difficulty
 * settings to a MineSweeper game.
 */
public class MediumStratergy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Medium", 14, 40);
    }
}
