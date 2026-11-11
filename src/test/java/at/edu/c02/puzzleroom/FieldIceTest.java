package at.edu.c02.puzzleroom;

import at.edu.c02.puzzleroom.commands.CommandLoad;
import at.edu.c02.puzzleroom.commands.CommandMove;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FieldIceTest {

    @Test
    public void iceStopsOnFirstPathAfterIce() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{
                "src/test/resources/icePath.maze"
        }).execute(gameBoard);

        new CommandMove(new String[]{"right"}).execute(gameBoard);

        Player player = gameBoard.getPlayer();
        assertEquals(1, player.getRow());
        assertEquals(4, player.getCol());
        assertEquals(3, player.getStepCount());
        assertFalse(gameBoard.isFinished());
    }

    @Test
    public void iceStopsOnLastIceBeforeWall() throws Exception {
        GameBoard gameBoard = new GameBoardImpl();
        new CommandLoad(new String[]{
                "src/test/resources/iceWall.maze"
        }).execute(gameBoard);

        new CommandMove(new String[]{"right"}).execute(gameBoard);

        Player player = gameBoard.getPlayer();
        assertEquals(1, player.getRow());
        assertEquals(3, player.getCol());
        assertEquals(2, player.getStepCount());
        assertFalse(gameBoard.isFinished());
    }
}