package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.commands.CommandLoad;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldTest {
    @Test
    public void startField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // We're using CommandLoad here - therefore it's not a full unit test, but allows us to test things easier
        // without having to duplicate the loading logic.
        // You will often find these constructs in "real life" applications (especially if tests were added later), when it's hard
        // to isolate everything.
        new CommandLoad(new String[]{"src/test/resources/startField.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start in 2nd row, 1st column
        assertEquals(2, player.getRow());
        assertEquals(1, player.getCol());
    }

    @Test
    public void pathField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start at 0 steps
        assertEquals(0, player.getStepCount());

        // Moving right should be possible, since there is a path field
        boolean success = player.moveRight();
        assertTrue(success);

        // Player should now be at 1 step
        assertEquals(1, player.getStepCount());
    }

    @Test
    public void wallField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Player should start at 0 steps
        assertEquals(0, player.getStepCount());

        // Moving left should not be possible, since there is a wall field
        boolean success = player.moveLeft();
        assertFalse(success);

        // Player should still be at 0 steps
        assertEquals(0, player.getStepCount());
    }

    @Test
    public void finishField() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        // Finish is reached when moving twice to the right
        new CommandLoad(new String[]{"src/test/resources/simple.maze"}).execute(gameBoard);
        Player player = gameBoard.getPlayer();

        // Game should not be finished after loading
        assertFalse(gameBoard.isFinished());
        player.moveRight();
        // Game should still not be finished after moving right once
        assertFalse(gameBoard.isFinished());
        player.moveRight();
        // Game should be finished after moving right twice
        assertTrue(gameBoard.isFinished());
    }


    @Test
    public void oneWayRightTest() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();

        new CommandLoad(new String[]{
                "src/test/resources/oneWayRight.maze"
        }).execute(gameBoard);

        Player player = gameBoard.getPlayer();

        // Move onto the arrow field
        assertTrue(player.moveRight());
        assertTrue(player.moveRight());

        // Player is now standing on '>'
        assertEquals(1, player.getRow());
        assertEquals(3, player.getCol());

        // Moving right must be allowed
        boolean success = player.moveRight();
        assertTrue(success);

        // Player must be right of the arrow
        assertEquals(1, player.getRow());
        assertEquals(4, player.getCol());

        // Three successful movements
        assertEquals(3, player.getStepCount());
    }


    @Test
    public void oneWayLeftBlockedTest() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();

        new CommandLoad(new String[]{
                "src/test/resources/oneWayRight.maze"
        }).execute(gameBoard);

        Player player = gameBoard.getPlayer();

        // Move onto the arrow field
        assertTrue(player.moveRight());
        assertTrue(player.moveRight());

        // Player is standing on '>'
        assertEquals(1, player.getRow());
        assertEquals(3, player.getCol());
        assertEquals(2, player.getStepCount());

        // Moving left must not be allowed
        boolean success = player.moveLeft();
        assertFalse(success);

        // Player must still be on the arrow
        assertEquals(1, player.getRow());
        assertEquals(3, player.getCol());

        // No additional step was taken
        assertEquals(2, player.getStepCount());
    }



}
