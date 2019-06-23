package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.GameEntities;
import org.academiadecodigo.batmancave.MovementController;
import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Players;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.maze.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Ghost extends Thread{

    // TODO rewrite all this!

    //properties
    private Position pos;
    private MovementController movementController;
    private Directions currentDirection;
    private Picture ghostGfx;
    private boolean[] moveVerifier;

    //private boolean playerDetected = false;     //irá no move verificar se tem algum player na range - se tiver, o estado de move altera



    public Ghost(Players player) {
        initGhostForPlayer(player);
        ghostGfx = new Picture(pos.getCol() * MazeGFX.CELLSIZE + MazeGFX.PADDING, pos.getRow() * MazeGFX.CELLSIZE + 8, "resources/ghost30.png");
        moveVerifier = new boolean[]{false,false,false,false};
    }

    private void initGhostForPlayer(Players player) {

        int quadrant = 0;

        if( player == Players.TWO) {
            quadrant = 1;
        }

        int[] tempPos = randomPos(quadrant);

        pos = new Position(tempPos[0], tempPos[1]);
        currentDirection = Directions.RIGHT;
    }

    public Position getPos(){
        return pos;
    }


    public void run() {

        while (true) {

            try {
                Thread.sleep(250 + (int)(Math.random()*10-5));
                if (movementController.askForToken()) {
                    motionControl();
                    movementController.returnToken();
                }

            } catch (InterruptedException iEx) {
                System.out.println("Interrupted Exception.");
            }

        }

    }


    //move method
    private void motionControl(){

        moveVerifier[0] = movementController.checkMove(pos, Directions.UP);
        moveVerifier[1] = movementController.checkMove(pos, Directions.RIGHT);
        moveVerifier[3] = movementController.checkMove(pos, Directions.LEFT);
        moveVerifier[2] = movementController.checkMove(pos, Directions.DOWN);

        int countPossibleMoves = 0;

        for (boolean dir:
             moveVerifier) {
            if(dir) {
                countPossibleMoves++;
            }
        }

        Directions nextMove;

        if (movementController.checkMove(pos, currentDirection)) {

            if(countPossibleMoves > 2) {
                moveVerifier[currentDirection.getOppositeDirection()] = false;  // If there are more than 2 moves can't got backwards.
                nextMove = RandomRoom.randomRoom(moveVerifier);                     // Chose random move except back
                currentDirection = nextMove;                                        // set new current direction
            } else {
                nextMove = currentDirection;                                        // keep current direction
            }

        } else {
            nextMove = RandomRoom.randomRoom(moveVerifier);
            currentDirection = nextMove;
        }

        pos.changePosition(nextMove.getMoveCol(), nextMove.getMoveRow());
        ghostGfx.translate(nextMove.getMoveCol() * MazeGFX.CELLSIZE, nextMove.getMoveRow() * MazeGFX.CELLSIZE);

        movementController.signalMove(GameEntities.GHOST);

    }

    private int[] randomPos (int quadrant) {

        int col = (int)(Math.random()*(41 / 2) + quadrant * 41 / 2);

        int row = (int)(Math.random()*(31 / 2) + quadrant * 31 / 2);

        int[] pos = {col, row};

        //System.out.println(col + ", " + row);

        if(col%2 != 0 && row%2 != 0) {
            return pos;
        } else {
            return randomPos(quadrant);
        }
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }

    public boolean caught(Player player) {

         return (pos.equals(player.getPos()) && player.isRetrieving());

    }


    public void draw(Player[] players) {
        //ghostGfx.draw();
        if (players[0].checkVisibility(pos) ||
            players[1].checkVisibility(pos)) {

                ghostGfx.draw();

        } else {
            ghostGfx.delete();
        }
    }

    public void hide() {
        ghostGfx.delete();
    }
}
