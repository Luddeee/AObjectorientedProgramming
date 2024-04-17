package Project;

public class EasyStrategy implements GameStrategy {
    public void applySettings(MineSweeper game) {
        game.setDifficulty("Easy", 8, 10);
    }
}
