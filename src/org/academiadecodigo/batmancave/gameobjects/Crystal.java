package org.academiadecodigo.batmancave.gameobjects;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Position;
import org.academiadecodigo.batmancave.maze.MazeGFX;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Crystal {

    private Position pos;
    private Position startPos;
    private Position[] possiblePos;
    private Picture crystalGfx;
    private boolean found;

    public Crystal() {
        possiblePos = new Position[3];
        pos = randomPosition();
        found = false;
        crystalGfx = new Picture(pos.getCol() * MazeGFX.CELLSIZE + MazeGFX.PADDING, pos.getRow() * MazeGFX.CELLSIZE + 8, "resources/power_crystal.png");
    }

    private Position randomPosition() {
        possiblePos[0] = new Position(1,3);  //1,29
        possiblePos[1] = new Position(3,1); //21,15
        possiblePos[2] = new Position(1, 3); //39,1

        int selectPos = (int)(Math.random() * possiblePos.length);

        return possiblePos[selectPos];
    }

    public void draw(Player[] players) {

        if(!found) {
            if (players[0].checkVisibility(pos) || players[1].checkVisibility(pos)) {
                crystalGfx.draw();
            } else {
                crystalGfx.delete();
            }
        } else {
            crystalGfx.delete();
        }
    }

    public Position getPos() {
        return pos;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public void restore() {
        found = false;
    }
}
