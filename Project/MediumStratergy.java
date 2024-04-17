package Project;

public class MediumStratergy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Medium", 14, 40);
    }
}
