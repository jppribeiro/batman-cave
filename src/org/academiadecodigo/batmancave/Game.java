package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

import java.io.File;
import java.net.URL;

public class Game {

    //Game
    private Menu menu;
    private Maze maze;
    private MovementController movementController;
    private Player[] players;
    private Ghost[] ghosts;
    private Flag flag;
    private boolean roundEnd;
    private int[] points;
    private GameStage stage;

    //Audio
    private Sound sound = new Sound();

    private File mainTheme = new File("workspace/myrepos/batman-cave/resources/startSong.wav");
    private File boo = new File("./resources/GOTCHA_BITCH.wav");
    private File escapeSong = new File("./resources/Danger.wav");
    private File powerUp = new File("./resources/Power_1.wav");
    private File hit = new File("./resources/moaning-woman_1.wav");



    private URL file = getClass().getResource("resources/Danger.wav");

    //Constructor
    public Game() {

        points = new int[]{0,0};

        maze = new Maze(41, 31);

        players = new Player[] {new PlayerOne(1,1), new PlayerTwo(39,29)};

        ghosts = new Ghost[2];

        movementController = new MovementController(maze, players, ghosts);

    }



    //Init Method
    public void init() {

        stage = GameStage.SEARCHING;

        maze.init();
        maze.generate();

        players[0].setMovementController(movementController);
        players[1].setMovementController(movementController);

        maze.draw(players);

        players[0].draw();
        players[1].draw();



        start();
    }

    public void start() {



    }


    private enum GameStage {
        SEARCHING,
        RETRIEVING
    }


}
