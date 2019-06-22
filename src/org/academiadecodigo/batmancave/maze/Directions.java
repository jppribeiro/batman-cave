package org.academiadecodigo.batmancave.maze;

public enum Directions {
    UP(2, 0, -1),
    RIGHT(3, 1,0),
    DOWN(0, 0, 1),
    LEFT(1, -1, 0 );

    int oppositeDirection;
    int moveCol;
    int moveRow;

    Directions(int oppositeDirection, int moveCol, int moveRow) {
        this.oppositeDirection = oppositeDirection;
        this.moveCol = moveCol;
        this.moveRow = moveRow;
    }

    public int getOppositeDirection() {
        return oppositeDirection;
    }

    public int getMoveCol() {
        return moveCol;
    }

    public int getMoveRow() {
        return moveRow;
    }
}
