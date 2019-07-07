package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.maze.Maze;


public class Game {

    //Game
    private Maze maze;

    //Constructor
    public Game() {

        //maze = new Maze(132, 33);
        maze = new Maze(113, 27);

    }


    //Init Method
    public void init() {

        maze.init();
        maze.generate();

        maze.draw();


    }

}
