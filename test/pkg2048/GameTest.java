package pkg2048;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
    private Game game = new Game();

    @Before
    public void setUp() {
        game.move("UP");
        game.addNumber();
        game.move("DOWN");
        game.addNumber();
        game.move("DOWN");
        game.addNumber();
        game.move("RIGHT");
        game.addNumber();
        game.move("LEFT");
        game.addNumber();
        game.move("DOWN");
        game.addNumber();
        game.move("RIGHT");
        game.addNumber();
    }

    @Test
    public void testStates() {
        assertEquals("WIN", State.WIN.name());
        assertEquals("CONTINUE", State.CONTINUE.name());
        assertEquals("LOSE", State.LOSE.name());
    }

    @Test
    public void testMovements() {
        assertEquals("UP", Movement.UP.name());
        assertEquals("DOWN", Movement.DOWN.name());
        assertEquals("LEFT", Movement.LEFT.name());
        assertEquals("RIGHT", Movement.RIGHT.name());
    }

    @Test
    public void testAddNumber() {
        int[][] table_before = game.getTable();
        int empty_boxes = 0;
        int filled_boxes = 0;
        int empty_boxes2 = 0;
        int filled_boxes2 = 0;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (table_before[x][y] == 0) {
                    empty_boxes++;
                } else {
                    filled_boxes++;
                }
            }
        }
        game.addNumber();

        int[][] table_after = game.getTable();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (table_after[x][y] == 0) {
                    empty_boxes2++;
                } else {
                    filled_boxes2++;
                }
            }
        }
        assertEquals("Since one number is added, the number of empty boxes must decrease by 1", empty_boxes, empty_boxes2 + 1);
        assertEquals("Since one number is added, the number of filled boxes must increase by 1", filled_boxes, filled_boxes2 - 1);
    }

    @Test
    public void testCheckFor2048() {
        assertFalse("Since add number method is called only 9 times, it is not possible" +
                "to reach 2048.", game.checkFor2048());
    }

    @Test
    public void testCheckBoardFull() {
        assertFalse("Since add number method is called only 9 times, it is not possible" +
                "to fill the game board.", game.checkBoardFull());
    }

    @After
    public void tearDown(){
        game = null;
        assertNull(game);
    }

}