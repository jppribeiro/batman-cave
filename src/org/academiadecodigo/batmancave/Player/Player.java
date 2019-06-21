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
    private boolean retrieving; // If player has found the crystal it is in a retrieving mode back to base


    public Player(int col, int row, Players type) {
        pos = new Position(col, row);
        hasFlag = false;
        this.type = type;
        viewRadius = 5;
        retrieving = false;
    }

    //walk method
    public abstract void keyAction(int key);

    public void draw() {
        playerGfx.draw();
    }

    public void hide() {
        playerGfx.delete();
    }

    public void setRetrieving(boolean retrieving) {
        this.retrieving = retrieving;
        if (retrieving) {
            lookFurious();
        } else {
            lookNormal();
        }

    }

    public boolean isRetrieving() {
        return retrieving;
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

    public boolean checkVisibility(Position pos) {

        int distance;

        distance = (int) (Math.sqrt((this.pos.getCol() - pos.getCol()) * (this.pos.getCol() - pos.getCol()) + (this.getPos().getRow() - pos.getRow()) * (this.getPos().getRow() - pos.getRow())));

        return (distance < viewRadius);
    }

    public void reset() {

        hasFlag = false;
        pos.resetPos();
    }

    public Players getType() {
        return type;
    }

    public abstract void lookFurious();

    public abstract void lookNormal();

    public boolean equals (Player obj) {
        if (this.getPos().getCol() == obj.getPos().getCol() && this.getPos().getRow() == obj.getPos().getRow()) {
            return true;
        }
        return false;
    }
}