package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.batmancave.MovementController;
import org.academiadecodigo.batmancave.Players;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class Player {

    //properties
    protected Position pos;
    private boolean hasFlag;
    private Players type;
    private Picture playerGfx;
    private int viewRadius;


    public Player(int col, int row, Players type) {
        pos = new Position(col, row);
        hasFlag = false;
        this.type = type;
        viewRadius = 5;

    }

    //walk method
    public abstract void keyAction(int key);

    public void draw() {
        playerGfx.draw();
    }

    public void hide() {
        playerGfx.delete();
    }

    public int getViewRadius() {
        return viewRadius;
    }

    public Picture getPlayerGfx() {
        return playerGfx;
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public Position getPos() {
        return pos;
    }

    public void setPlayerGfx(Picture playerGfx) {
        this.playerGfx = playerGfx;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public abstract void setMovementController(MovementController movementController);



    public void reset() {

        hasFlag = false;
        pos.resetPos();
    }

    public Players getType() {
        return type;
    }


    public boolean equals (Player obj) {
        if (this.getPos().getCol() == obj.getPos().getCol() && this.getPos().getRow() == obj.getPos().getRow()) {
            return true;
        }
        return false;
    }
}