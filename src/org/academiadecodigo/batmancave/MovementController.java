package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
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
    private boolean ghostsAwake;
    private GameStage stage;


    public MovementController (Maze maze, Crystal crystal, Player[] players, Ghost[] ghosts) {
        this.maze = maze;
        this.crystal = crystal;
        this.players = players;
        this.ghosts = ghosts;
        ghostsAwake = false;
    }


    public void signalMove(GameEntities entity) {
        // If a ghost or a player tries to move and checkMove() returns true then the entity that moved has to send
        // a signal that it has finished moving in order to check for game events and to redraw the screen.

        // check if player caught crystal
        if (!crystal.isFound() && crystal.getPos().equals(players[0].getPos())) {

            ((PlayerOne)players[0]).caughtCrystal();
            crystal.setFound(true);
            stage = GameStage.RETRIEVING;

        } else if (!crystal.isFound() && crystal.getPos().equals(players[1].getPos())) {

            ((PlayerTwo)players[1]).caughtCrystal();
            crystal.setFound(true);
            stage = GameStage.RETRIEVING;
        }

        // If a player caught the crystal call the ghosts:: ghostsAwake is a trigger to instantiate only once
        if(stage == GameStage.RETRIEVING && !ghostsAwake) {
            // Instantiate ghosts and start their thread

            ghosts[0] = new Ghost(Players.ONE);
            ghosts[0].setMovementController(this);
            ghosts[0].start();

            ghosts[1] = new Ghost(Players.TWO);
            ghosts[1].setMovementController(this);
            ghosts[1].start();

            ghostsAwake = true;

        }

        // check if player stole crystal from another player
        if (players[0].getPos().equals(players[1].getPos())) {
            if (players[0].isRetrieving()) {

                ((PlayerOne)players[0]).lostCrystal();
                ((PlayerTwo)players[1]).caughtCrystal();

            } else if (players[1].isRetrieving()) {

                ((PlayerTwo)players[1]).lostCrystal();
                ((PlayerOne)players[0]).caughtCrystal();

            }
        }

        // check if ghost caught player
        if (ghostsAwake) {
            checkPlayersCaught();
        }

        // TODO check if player won


        // refresh game screen
        refreshScreen(entity);

    }

    private void checkPlayersCaught() {

        if (ghosts[0].caught(players[0])) {

            ((PlayerOne)players[0]).lostCrystal();
            crystal.restore();
            stage = GameStage.SEARCHING;

        } else if (ghosts[0].caught(players[1])) {

            ((PlayerTwo)players[1]).lostCrystal();
            crystal.restore();
            stage = GameStage.SEARCHING;

        }

        if (ghosts[1].caught(players[0])) {

            ((PlayerOne)players[0]).lostCrystal();
            crystal.restore();
            stage = GameStage.SEARCHING;

        } else if (ghosts[1].caught(players[1])) {

            ((PlayerTwo)players[1]).lostCrystal();
            crystal.restore();
            stage = GameStage.SEARCHING;

        }
    }

    public boolean checkMove(Position pos, Directions direction) {

        Cell cellToMove = maze.getLayout()[pos.getCol() + direction.getMoveCol()][pos.getRow() + direction.getMoveRow()];

        return cellToMove.getType() == CellType.ROOM;
    }

    private void refreshScreen(GameEntities entity) {

        // ghosts are working in a separate thread:: If we don't have this switch we would get a nullPointerEx when signalMove() calls
        // this method because the ghost would be calling the redraw of the entire maze at the same time

        // GLITCH!! Right now ghosts are being redrawn each time they move. If we ALSO had them being redrawn when the player moved we
        // would eventually get a nullPointerEx because we would be trying to delete something at the same time or accessing an array while deleting!

        switch (entity) {
            case PLAYER:
                maze.draw(players);

                crystal.draw(players);

                players[0].hide();
                players[1].hide();

                players[0].draw();
                players[1].draw();

                break;
            case GHOST:
                for (Ghost ghost:
                        ghosts) {
                    if (ghost != null) {
                        // DRAW GHOST
                        ghost.draw(players);
                    }
                }
            default:
                break;
        }
    }


    private enum GameStage {
        SEARCHING,
        RETRIEVING
    }

}
