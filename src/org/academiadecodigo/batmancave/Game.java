package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gameobjects.enemies.GhostSelector;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.Usables.*;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;
import java.io.File;

public class Game {

    private Menu menu = new Menu();
    private Maze maze = new Maze(41, 31);
    private MazeGfx mazeGfx = new MazeGfx(maze);
    private Player[] players = new Player[2];
    private Ghost[] ghosts = new Ghost[2];
    private Flag flag = new Flag();
    private MovementDetector movementDetector = new MovementDetector(maze, flag);
    private boolean roundEnd;
    private boolean p1w;
    private int[] points;
    private GameStage stage;

    //Audio
    private Sound soundMenuSong = new Sound();
    private Sound soundBoo = new Sound();
    private Sound soundGameSong = new Sound();
    private Sound soundPowerUp = new Sound();
    private Sound soundHit = new Sound();
    private File menuSong = new File("./resources/startSong.wav");
    private File boo = new File("./resources/GOTCHA_BITCH.wav");
    private File gameSong = new File("./resources/Danger.wav");
    private File powerUp = new File("./resources/Power_1.wav");
    private File hit = new File("./resources/moaning-woman_1.wav");

    //Init Method
    public void init() {
        while(true) {
            try {
                soundMenuSong.play(menuSong);
                menu.keyboard();
                menu.startMenu();
                soundMenuSong.stop();
                soundGameSong.play(gameSong);

                start();
                soundGameSong.stop();
                if (p1w) {
                    menu.setP1wins();
                } else {
                    menu.setP2wins();
                }
                menu.gameOver();
                menu.setGameStart();
            } catch (InterruptedException e) {
                System.out.println("Interrupted Exception");
            }
        }
    }

    public void start() throws InterruptedException {
        int powerUpCounter = 0;

        points = new int[]{0,0};
        stage = GameStage.SEARCHING;

        maze.init();
        maze.generate();

        players[0] = new PlayerOne(1,1);
        players[1] = new PlayerTwo(maze.getLayout().length-2, maze.getLayout()[0].length-2);

        players[0].setMovementDetector(movementDetector);
        players[1].setMovementDetector(movementDetector);

        players[0].setMazeGfx(mazeGfx);
        players[1].setMazeGfx(mazeGfx);

        mazeGfx.setFlag(flag);
        mazeGfx.init();
        mazeGfx.setPlayers(players);

        players[0].walk();
        players[1].walk();

        roundEnd = false;
        players[0].setGameStart(roundEnd);
        players[1].setGameStart(roundEnd);

        while (!roundEnd) {
            Thread.sleep(5);
            // Make condition to win level and raise level
            if(stage == GameStage.SEARCHING) {
                if(players[0].getHasFlag() || players[1].getHasFlag()) {
                    stage = GameStage.RETRIEVING;

                    ghosts[0] = new Ghost(1);
                    ghosts[1] = new Ghost(2);

                    ghosts[0].setMazeGfx(mazeGfx);
                    ghosts[0].setMovementDetector(movementDetector);

                    ghosts[1].setMazeGfx(mazeGfx);
                    ghosts[1].setMovementDetector(movementDetector);

                    mazeGfx.drawGhost(ghosts);
                }
            } else if (stage == GameStage.RETRIEVING) {
                if (powerUpCounter == 0) {
                    soundPowerUp.playSFX(powerUp);
                    powerUpCounter++;
                }

                ghosts[0].move(GhostSelector.ONE);
                ghosts[1].move(GhostSelector.TWO);

                Player dead = movementDetector.killedByGhost(ghosts, players);

                if(dead != null) {
                    if (dead.getHasFlag()) {
                        soundBoo.playSFX(boo);
                        dead.reset();
                        mazeGfx.playerCaught(dead.getType());
                    }
                }

                roundEnd = movementDetector.roundEnd(players);
            }

            players[0].setGameStart(roundEnd);
            players[1].setGameStart(roundEnd);

            if(movementDetector.playersClash(players)) {
                soundHit.playSFX(hit);
                Thread.sleep(500);
            }
        }

        if (players[0].getHasFlag()) {
            p1w = true;
            points[0]++;
        } else {
            points[1]++;
        }

        flag.resetFlag();
        players[0].reset();
        players[1].reset();
        mazeGfx.deleteMazeGfx();
    }

    private enum GameStage {
        SEARCHING,
        RETRIEVING
    }
}
