package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gameobjects.Crystal;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;


public class Game {

    //Game
    private Menu menu;
    private Maze maze;
    private Crystal crystal;
    private MovementController movementController;
    private Player[] players;
    private Ghost[] ghosts;

    //Audio
    private Sound sound = new Sound();

    private String mainTheme = "/resources/startSong.wav";
    private String boo = "/resources/GOTCHA_BITCH.wav";
    private String escapeSong = "/resources/Danger.wav";
    private String powerUp = "/resources/Power_1.wav";
    private String hit = "/resources/moaning-woman_1.wav";

    //Constructor
    public Game() {

        maze = new Maze(41, 31);

        crystal = new Crystal();

        players = new Player[] {new PlayerOne(1,1), new PlayerTwo(39,29)};

        ghosts = new Ghost[2];

        movementController = new MovementController(maze, crystal, players, ghosts);

    }


    public void menu() {

        try {
            //sound.play(mainTheme, true);

            menu = new Menu();

            while (!menu.isGameStart()) {
                Thread.sleep(500);
            }
            //sound.stop();
            //sound.play(escapeSong, true);
            init();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    //Init Method
    public void init() {

        maze.init();
        maze.generate();

        players[0].setMovementController(movementController);
        players[1].setMovementController(movementController);

        maze.setMovementController(movementController);

        maze.draw(players);

        players[0].draw();
        players[1].draw();

    }

}
