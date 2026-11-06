package at.edu.c02.puzzleroom.commands;

import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.Player;
import at.edu.c02.puzzleroom.exceptions.PuzzleRoomException;

public class CommandFastmove implements Command {
    private final String[] direction;

    public CommandFastmove(String[] arguments) throws PuzzleRoomException {
        if (arguments.length < 1) {
            throw new IllegalArgumentException("Invalid number of arguments for fastmove command");
        }

        this.direction = arguments;

    }

    @Override
    public void execute (GameBoard gameBoard) throws PuzzleRoomException {

       boolean success = isSuccess(gameBoard);

        CommandShow showCommand = new CommandShow();
        showCommand.execute(gameBoard);

        if (!success) {
            throw new PuzzleRoomException("Invalid move");
        }
    }

    private boolean isSuccess(GameBoard gameBoard) throws PuzzleRoomException {

        Player player = gameBoard.getPlayer();
        if (player == null) {
            throw new PuzzleRoomException("Player not found");
        }

        for (String dir : direction) {
             boolean thisSuccess = switch (dir.toLowerCase()) {
                case "u" -> player.moveUp();
                case "d" -> player.moveDown();
                case "l" -> player.moveLeft();
                case "r" -> player.moveRight();
                default -> throw new PuzzleRoomException("Invalid direction: " + dir);
            };

            if(!thisSuccess) {
                return false;
            }
        }

        return true;
    }
}
