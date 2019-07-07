package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.maze.Maze;

public class Main {

    public static void main(String[] args) {

        Maze maze = new Maze(71, 27);

        maze.init();

        maze.generate();

        maze.draw();

    }
}
