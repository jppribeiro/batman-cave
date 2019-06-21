package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;
import org.academiadecodigo.batmancave.maze.Cell;
import org.academiadecodigo.batmancave.maze.CellType;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.Maze;

public class MovementController {

    private Maze maze;
    private Player[] players;
    private Ghost[] ghosts;

    public MovementController (Maze maze, Player[] players, Ghost[] ghosts) {
        this.maze = maze;
        this.players = players;
        this.ghosts = ghosts;
    }


    public void signalMove() {
        // If a ghost or a player tries to move and checkMove() returns true then the entity that moved has to send
        // a signal that it has finished moving to redraw the screen.

        refreshScreen();

    }


    public boolean checkMove(Position pos, Directions direction) {
        Cell cellToMove = maze.getLayout()[pos.getCol() + direction.getMoveCol()][pos.getRow() + direction.getMoveRow()];

        return cellToMove.getType() == CellType.ROOM;
    }

    private void refreshScreen() {

        maze.draw(players);

        players[0].hide();
        players[1].hide();

        players[0].draw();
        players[1].draw();

        for (Ghost ghost:
             ghosts) {
            if (ghost != null) {
                // DRAW GHOST
            }
        }


    }
}
