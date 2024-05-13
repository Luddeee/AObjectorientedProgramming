package Project;

import org.junit.Test;

class MineSweeperTest {

    @Test
    void testInitialization() {
        MineSweeper mineSweeper = new MineSweeper();
        assertNotNull(mineSweeper.getMainPanel(), "Main panel should not be null after initialization");
        assertEquals(8, mineSweeper.getRow(), "Default row should be 8 for easy difficulty");
    }

    @Test
    void testRestart() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.restart();
        assertEquals(1, mineSweeper.getrestartcount(), "Restart count should be 1 after one restart");
        assertFalse(mineSweeper.isLost, "isLost should be false after restart");
    }

    @Test
    void testSaveHighscore() {
        MineSweeper mineSweeper = new MineSweeper();
        HighScoresManager mockManager = Mockito.mock(HighScoresManager.class);
        mineSweeper.highScoresManager = mockManager;
        
        mineSweeper.saveHighscore(12345, "TestPlayer");
        verify(mockManager, times(1)).updateHighScores(anyString(), eq("TestPlayer"), eq(12345L));
    }

    @Test
    void testGetCellStatus() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.restart(); // Set up a new game
        assertEquals("U", mineSweeper.getCellStatus(0, 0), "New cell should be unopened");
    }
}