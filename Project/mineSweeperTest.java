package Project;

import org.junit.Test;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class mineSweeperTest {

    @Test
    void testSetDifficulty() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Easy", 8, 10);
        assertEquals(8, mineSweeper.getRow());
        assertEquals(10, mineSweeper.numMines);
    }

    @Test
    void testNearBombsCounter() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.row = 5;
        mineSweeper.numMines = 5;
        mineSweeper.initializeGrid();
        // Assuming a mine is at position (1,1)
        mineSweeper.mines.add("1 1");
        assertEquals(1, mineSweeper.nearBombsCounter(0, 0));
        assertEquals(1, mineSweeper.nearBombsCounter(0, 1));
        assertEquals(1, mineSweeper.nearBombsCounter(1, 0));
        assertEquals(1, mineSweeper.nearBombsCounter(1, 2));
        assertEquals(1, mineSweeper.nearBombsCounter(2, 1));
        assertEquals(0, mineSweeper.nearBombsCounter(2, 2));
    }

    @Test
    void testShowButton() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.row = 3;
        mineSweeper.numMines = 1;
        mineSweeper.initializeGrid();
        // Assuming a mine is at position (0,0)
        mineSweeper.mines.add("0 0");
        mineSweeper.showButton(0, 1);
        assertTrue(mineSweeper.visited[0][1]);
        assertFalse(mineSweeper.isLost);
        mineSweeper.showButton(0, 0); // Clicking on a mine
        assertTrue(mineSweeper.isLost);
    }

    // Add more test cases for other methods as needed...

}
