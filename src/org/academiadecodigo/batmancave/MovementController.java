package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.gameobjects.Crystal;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;
import org.academiadecodigo.batmancave.maze.Cell;
import org.academiadecodigo.batmancave.maze.CellType;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.Maze;

public class MovementController {

    private Maze maze;
    private Crystal crystal;
    private Player[] players;
    private Ghost[] ghosts;

    public MovementController (Maze maze, Crystal crystal, Player[] players, Ghost[] ghosts) {
        this.maze = maze;
        this.crystal = crystal;
        this.players = players;
        this.ghosts = ghosts;
    }


    public void signalMove() {
        // If a ghost or a player tries to move and checkMove() returns true then the entity that moved has to send
        // a signal that it has finished moving to redraw the screen.

        // check if player caught crystal
        if (crystal.getPos().equals(players[0].getPos())) {
            players[0].setRetrieving(true);
            crystal.setFound(true);
        } else if (crystal.getPos().equals(players[1].getPos())) {
            players[1].setRetrieving(true);
            crystal.setFound(true);
        }

        // check if player stole crystal from another player
        if (players[0].getPos().equals(players[1].getPos())) {
            if (players[0].isRetrieving()) {
                players[0].setRetrieving(false);
                players[1].setRetrieving(true);
            } else if (players[1].isRetrieving()) {
                players[1].setRetrieving(false);
                players[0].setRetrieving(true);
            }
        }

        // check if ghost caught player

        refreshScreen();

    }


    public boolean checkMove(Position pos, Directions direction) {
        Cell cellToMove = maze.getLayout()[pos.getCol() + direction.getMoveCol()][pos.getRow() + direction.getMoveRow()];

        return cellToMove.getType() == CellType.ROOM;
    }

    private void refreshScreen() {

        maze.draw(players);

        crystal.draw(players);

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
