package org.academiadecodigo.batmancave.gameobjects.Usables;

import org.academiadecodigo.batmancave.Position;

public class Flag {
    private Position pos;
    private Position startPos;
    private Position[] possiblePositions;


    //TODO set random start position middle vs corners
    public Flag() {
        possiblePositions = new Position[3];
        pos = randomPosition();
    }

    private Position randomPosition() {
        possiblePositions[0] = new Position(1,29);
        possiblePositions[1] = new Position(21,15);
        possiblePositions[2] = new Position(39, 1);

        int selectPos = (int)(Math.random()*possiblePositions.length);

        return possiblePositions[selectPos];
    }

    public void resetFlag() {
        pos = randomPosition();
    }

    public Position getPos() {
        return pos;
    }

    public Position getStartPos() {
        return startPos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
