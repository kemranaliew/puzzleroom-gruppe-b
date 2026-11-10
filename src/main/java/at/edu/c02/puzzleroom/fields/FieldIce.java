package at.edu.c02.puzzleroom.fields;

import at.edu.c02.puzzleroom.Direction;
import at.edu.c02.puzzleroom.GameBoard;
import at.edu.c02.puzzleroom.Player;

public class FieldIce extends BaseField {

    public FieldIce(GameBoard gameBoard, char name, int row, int col) {
        super(gameBoard, name, row, col);
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean enterField(Direction direction) {
        Player player = gameBoard.getPlayer();
        player.walkStep();
        setPlayerPositionToField();

        // Vom Eis aus einen weiteren Schritt in derselben Richtung versuchen.
        switch (direction) {
            case Up -> player.moveUp();
            case Down -> player.moveDown();
            case Left -> player.moveLeft();
            case Right -> player.moveRight();
        }

        // Eine Wand beendet nur das Rutschen; dieses Eisfeld wurde betreten.
        return true;
    }

    @Override
    public boolean leaveField(Direction direction) {
        return true;
    }
}