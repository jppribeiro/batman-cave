package org.academiadecodigo.batmancave;

import org.academiadecodigo.batmancave.Player.Player;
import org.academiadecodigo.batmancave.Player.PlayerOne;
import org.academiadecodigo.batmancave.Player.PlayerTwo;
import org.academiadecodigo.batmancave.gfx.MazeGfx;
import org.academiadecodigo.batmancave.maze.Maze;
import org.academiadecodigo.batmancave.maze.MovementDetector;
import org.academiadecodigo.batmancave.gameobjects.enemies.Ghost;

import javax.sound.sampled.*;
import java.io.File;

public class Game {

    private Maze maze;
    private MazeGfx mazeGfx;
    private MovementDetector movementDetector;
    private PlayerOne playerOne;
    private PlayerTwo playerTwo;
    private Ghost ghost;
    private int gameLevel;

    public Game() {
        maze = new Maze(41, 31);
        mazeGfx = new MazeGfx(maze);
        gameLevel = 1;

    }

    public void init() {

        runSound();

        maze.init();

        maze.generate();

        mazeGfx.init();

        playerOne = new PlayerOne(0,1);

        playerTwo = new PlayerTwo(maze.getLayout().length-1, maze.getLayout()[0].length-2);

        ghost = new Ghost(31,15);

        movementDetector = new MovementDetector(maze, ghost);

        playerOne.setMovementDetector(movementDetector);

        playerOne.setMazeGfx(mazeGfx);

        ghost.setMovementDetector(movementDetector);

        ghost.setMazeGfx(mazeGfx);

        playerTwo.setMovementDetector(movementDetector);

        playerTwo.setMazeGfx(mazeGfx);

    }

    public void start() throws InterruptedException{
        playerOne.walk();
        playerTwo.walk();


        while(true) {

            Thread.sleep(100);
            // Move Ghost
            ghost.move();

            // Make condition to win level and raise level
        }
    }

    public void runSound() {
        try {

            File clipFile = new File("./resources/atTheEndOfAllThings.wav"); // path to your clip
            AudioInputStream audioStrmObj = AudioSystem.getAudioInputStream(clipFile);
            AudioFormat format = audioStrmObj.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStrmObj);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("NOT");
        }
    }

    //gameLevel


    //getGameLevel method
    public int getGameLevel() {
        return gameLevel;
    }

    //setGameLevel method
    public void setGameLevel(int gameLevel) {
        this.gameLevel = gameLevel;
    }
}
