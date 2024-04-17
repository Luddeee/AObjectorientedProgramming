package Project;

public class HardStratergy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Hard", 24, 99);
    }
}
