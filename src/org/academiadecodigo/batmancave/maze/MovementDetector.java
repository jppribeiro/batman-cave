package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.batmancave.Player;

public class MovementDetector {

    private Maze maze;
    private Player player;

    public MovementDetector(Maze maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    public boolean checkMove(Directions direction) {

        int currentCol = player.getPos().getCol();
        int currentRow = player.getPos().getRow();



        switch (direction) {
            case UP:
                if (maze.getLayout()[currentCol][currentRow - 1].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case RIGHT:
                if (maze.getLayout()[currentCol + 1][currentRow].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case LEFT:
                if (maze.getLayout()[currentCol - 1][currentRow].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            case DOWN:
                if (maze.getLayout()[currentCol][currentRow + 1].getType() == CellType.ROOM) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }


    }



}