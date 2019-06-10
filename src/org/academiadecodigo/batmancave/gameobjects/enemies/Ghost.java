package org.academiadecodigo.batmancave.gameobjects.enemies;

import org.academiadecodigo.batmancave.Game;
import org.academiadecodigo.batmancave.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Directions;
import org.academiadecodigo.batmancave.maze.MovementDetector;

import java.util.ArrayList;
import java.util.List;

public class Ghost extends Enemy {

    //properties
    private int ghostLevel;
    private int speed = 1;
    private Position pos;
    private MovementDetector movementDetector;
    private MazeGfx mazeGfx;
    private int value;

    public Ghost() {
        ghostLevel = 1;
    }



    //move method
    public void move () {
        value = (int) Math.round(Math.random()*4);
        switch (ghostLevel) {
            case 1:
                // Level 1 behaviour: random movement
                if (movementDetector.checkMove(Directions.UP)) {
                    pos.changePosition(0,-1);
                    mazeGfx.moveGhost(0, -1);
                    try {
                        Thread.sleep(mazeGfx.getGhostDelay());
                    } catch (InterruptedException ex) {
                        System.out.println("");
                    }

                } else if (movementDetector.checkMove(Directions.LEFT)) {
                    pos.changePosition(0,-1);
                    mazeGfx.moveGhost(0, -1);
                    try {
                        Thread.sleep(mazeGfx.getGhostDelay());
                    } catch (InterruptedException ex) {
                        System.out.println("");
                    }
                }


                break;
            case 2:
                // Level 2 behaviour: follows if player is seen in a straight line

                break;
            case 3:
                // follows player after first encounter

                break;
            default:
                break;
        }
    }



    public void setGhostLevel(int ghostLevel) {
        this.ghostLevel = ghostLevel;
    }

    public void setMazeGfx(MazeGfx mazeGfx) {
        this.mazeGfx = mazeGfx;
    }

    public void setMovementDetector(MovementDetector movementDetector) {
        this.movementDetector = movementDetector;
    }

    //hit method
    @Override
    public void hit (Player player) {
        player.die();
        getMessage();
    }

    //getMessage method
    @Override
    public void getMessage() {
        System.out.println("*UUUUUUUUUUUHH*");
    }
}
