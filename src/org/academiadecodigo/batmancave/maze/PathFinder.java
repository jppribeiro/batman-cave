package org.academiadecodigo.batmancave.maze;

import org.academiadecodigo.simplegraphics.graphics.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathFinder {
    private Stack<int[]> stack;
    private Cell[][] layout;
    private int numSteps;
    private final int[] noMove = {0,0};
    private Color path;

    public PathFinder(Cell[][] layout) {
        stack = new Stack<>();
        this.layout = layout;
        numSteps = 0;
        path = new Color(255, 250, 133);
    }


    private Directions getDirectionToMove(boolean[] availableRooms) {

        int col = stack.get(stack.size()-1)[0];
        int row = stack.get(stack.size()-1)[1];

        // UP
        if(row - 1 > 0 &&
                layout[col][row - 1].getType() == CellType.ROOM &&
                !layout[col][row - 1].isVisited()) {
            availableRooms[0] = true; // Room is available
        }

        // RIGHT
        if(col + 1 < layout.length &&
                layout[col + 1][row].getType() == CellType.ROOM &&
                !layout[col + 1][row].isVisited()) {
            availableRooms[1] = true; // Room is available
        }

        // DOWN
        if(row + 1 < layout[0].length &&
                layout[col][row + 1].getType() == CellType.ROOM &&
                !layout[col][row + 1].isVisited()) {
            availableRooms[2] = true; // Room is available
        }

        // LEFT
        if(col - 1 > 0 &&
                layout[col - 1][row].getType() == CellType.ROOM &&
                !layout[col - 1][row].isVisited()) {
            availableRooms[3] = true; // Room is available
        }

        return RandomRoom.randomRoom(availableRooms);
    }

    public int[] move() {

        // Get current position stored in stack

        int col = stack.get(stack.size()-1)[0];
        int row = stack.get(stack.size()-1)[1];

        layout[col][row].setVisited(true);


        boolean[] possibleRooms = {false, false, false, false}; // UP - RIGHT - DOWN - LEFT

        Directions move = getDirectionToMove(possibleRooms);

        if (move == null) {
            return noMove;
        }

        int nextCol = 0;
        int nextRow = 0;

        switch (move) {
            case UP:
                nextCol = 0;
                nextRow = -1;
                break;
            case RIGHT:
                nextCol = 1;
                nextRow = 0;
                break;
            case DOWN:
                nextCol = 0;
                nextRow = 1;
                break;
            case LEFT:
                nextCol = -1;
                nextRow = 0;
                break;
            default:
                nextCol = 0;
                nextRow = 0;
                break;
        }

        int totalAvailableRooms = countAvailableRooms(possibleRooms);

        /*
        for(int i = 0; i < availableRooms.length; i++) {
            if(availableRooms[i]) {
                totalAvailableRooms++;
            }
        }

         */

        int[] moveTo = {0, 0};

        if(totalAvailableRooms > 4 || totalAvailableRooms < 1) {
            return moveTo;
        } else {
            moveTo[0] = col + nextCol;
            moveTo[1] = row + nextRow;
            return moveTo;
        }

    }

    public int countAvailableRooms(boolean[] arr) {

        int roomCounter = 0;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i]) {
                roomCounter++;
            }
        }

        return roomCounter;
    }

    public Stack<int[]> getStack() {
        return stack;
    }

    public int getNumSteps() {
        return numSteps;
    }

    public void addStep() {
        numSteps++;
    }
}
