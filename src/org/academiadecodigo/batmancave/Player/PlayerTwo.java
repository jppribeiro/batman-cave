package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.MovementController;
import org.academiadecodigo.batmancave.Players;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MazeGFX;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class PlayerTwo extends Player {

    private KeyboardController keyboardController;
    private MovementController movementController;

    public PlayerTwo(int col, int row) {
        super(col, row, Players.TWO);
        super.setPlayerGfx(new Picture(col * MazeGFX.CELLSIZE + MazeGFX.PADDING, row * MazeGFX.CELLSIZE + 8, "resources/Player/player 2 30x30.png"));
        keyboardController = new KeyboardController(this);
        setupKeys();
    }

    private void setupKeys() {
        keyboardController.addEventPressKey(KeyboardEvent.KEY_W);
        keyboardController.addEventPressKey(KeyboardEvent.KEY_D);
        keyboardController.addEventPressKey(KeyboardEvent.KEY_S);
        keyboardController.addEventPressKey(KeyboardEvent.KEY_A);
    }

    public void keyAction(int key) {

        switch (key) {
            case KeyboardEvent.KEY_UP:
                // TODO MOVE UP
                move(Directions.UP);
                break;
            case KeyboardEvent.KEY_RIGHT:
                // TODO MOVE RIGHT
                move(Directions.RIGHT);
                break;
            case KeyboardEvent.KEY_DOWN:
                // TODO MOVE DOWN
                move(Directions.DOWN);
                break;
            case KeyboardEvent.KEY_LEFT:
                // TODO MOVE LEFT
                move(Directions.LEFT);
                break;
            default:
                break;

        }

    }

    private void move(Directions direction) {

        if (movementController.checkMove(pos, direction)) {

            pos.changePosition(direction.getMoveCol(), direction.getMoveRow());
            getPlayerGfx().translate(direction.getMoveCol() * MazeGFX.CELLSIZE, direction.getMoveRow() * MazeGFX.CELLSIZE);

            movementController.signalMove();
        }

    }

    @Override
    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

}
