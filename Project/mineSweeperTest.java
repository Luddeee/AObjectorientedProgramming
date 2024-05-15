package Project;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class mineSweeperTest {

    @Test
    public void testInit() { //starting diff is easy thus 8 rows and 10 mines
    MineSweeper mineSweeper = new MineSweeper();
    assertEquals(8, mineSweeper.getRow());
    assertEquals(5, mineSweeper.numMines);
    assertNotNull(mineSweeper.getMainPanel());
    }

    @Test
    public void testSetDifficulty() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Medium", 14, 40);
        assertEquals(14, mineSweeper.getRow());
        assertEquals(40, mineSweeper.numMines);

        mineSweeper.setDifficulty("Hard", 10, 60);
        assertEquals(10, mineSweeper.getRow());
        assertEquals(60, mineSweeper.numMines);
    }

    @Test
    public void testNearBombsCounter() { //in this test i add mines manually to be able to know where they are
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Medium", 10, 0); //I start with 0 mines in order to add them manually
        mineSweeper.initializeGrid();

        mineSweeper.mines.add("1 1");
        mineSweeper.mines.add("1 2");
        mineSweeper.mines.add("1 3");

        assertEquals(1, mineSweeper.nearBombsCounter(0, 0));
        assertEquals(3, mineSweeper.nearBombsCounter(2, 2));
        assertEquals(2, mineSweeper.nearBombsCounter(0, 1));
        assertEquals(1, mineSweeper.nearBombsCounter(2, 0));
        assertEquals(2, mineSweeper.nearBombsCounter(2, 3));
        assertEquals(1, mineSweeper.nearBombsCounter(1, 4));
    }

    @Test
    public void testShowButton() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Easy", 5, 0);
        mineSweeper.initializeGrid();
        mineSweeper.mines.clear();
        mineSweeper.mines.add("2 2");

        mineSweeper.showButton(0, 0);
        assertTrue(mineSweeper.visited[0][0]);
        assertFalse(mineSweeper.visited[2][2]);
        mineSweeper.showButton(1, 2);
        assertTrue(mineSweeper.visited[1][2]);
    }  

    @Test
        public void testToggleFlag() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Easy", 3, 3);
        mineSweeper.initializeGrid();
        assertFalse(mineSweeper.flagged[1][1]);

        mineSweeper.toggleFlag(1, 1);
        assertTrue(mineSweeper.flagged[1][1]);

        mineSweeper.toggleFlag(1, 1);
        assertFalse(mineSweeper.flagged[1][1]);
    }   

    @Test
    public void testRestart() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Easy", 5, 0);
        mineSweeper.initializeGrid();
        mineSweeper.mines.add("1 1");
        mineSweeper.mines.add("1 2");
        mineSweeper.mines.add("1 3");

        assertEquals(1, mineSweeper.getrestartcount());

        mineSweeper.showButton(0, 0);
        mineSweeper.toggleFlag(1, 1);
        mineSweeper.showButton(2, 1);
        mineSweeper.isLost = true;

        mineSweeper.restart();

        assertFalse(mineSweeper.isLost);
        assertEquals(0, mineSweeper.wincounter);
        assertFalse(mineSweeper.visited[0][0]);
        assertFalse(mineSweeper.flagged[1][1]);
        assertEquals(2, mineSweeper.getrestartcount());

        mineSweeper.restart();
        assertEquals(3, mineSweeper.getrestartcount());
    }

    @Test
    public void testGetCellStatus() {
        MineSweeper mineSweeper = new MineSweeper();
        mineSweeper.setDifficulty("Easy", 10, 0);
        mineSweeper.initializeGrid();
        mineSweeper.mines.add("0 0");
        mineSweeper.mines.add("0 1");

        mineSweeper.showButton(1, 2); 
        assertEquals("1", mineSweeper.getCellStatus(1, 2));

        mineSweeper.showButton(1, 1); 
        assertEquals("2", mineSweeper.getCellStatus(1, 1));

        mineSweeper.showButton(2, 2); 
        assertEquals("0", mineSweeper.getCellStatus(2, 2));

        mineSweeper.toggleFlag(0, 0);
        assertEquals("F", mineSweeper.getCellStatus(0, 0));
    }
}
