package org.academiadecodigo.batmancave;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Menu implements KeyboardHandler {
    private boolean gameStart;
    private boolean buttonPress;
    private boolean buttonPress1;
    private boolean buttonReleased;
    private Picture bg = new Picture(10,10,"Menu/background.png");
    private Picture start = new Picture(483, 550, "Menu/buttons/Start.png");
    private Picture quit = new Picture(483, 650, "Menu/buttons/Quit.png");
    private Picture button = new Picture(483, 550, "Menu/buttons/button.png");
    private Picture button1 = new Picture(483, 650, "Menu/buttons/button.png");
    private Picture buttonPressed = new Picture(483, 550, "Menu/buttons/button pressed.png");
    private Picture buttonPressed1 = new Picture(483, 650, "Menu/buttons/button pressed.png");
    private Picture pressSpace = new Picture(539, 565, "Menu/buttons/Press space.png");
    private Picture pressEsc = new Picture(539, 665, "Menu/buttons/Press esc.png");
    private Picture title = new Picture(376, 180, "Menu/tittle.png");
    private Picture bg1 = new Picture(10,10, "Menu/background1.png");
    private Picture gameOver = new Picture(381,300, "Menu/game_over.png");
    private Picture p1win = new Picture(475, 600,"Menu/p1w.png");
    private Picture p2win = new Picture(475, 600,"Menu/p2w.png");


    public void startMenu(){
        bg.draw();
        title.draw();
        button.draw();
        button1.draw();
        start.draw();
        quit.draw();

        while (!gameStart){
            if (!buttonPress) {
                start.delete();
                pressSpace.draw();
            }
            if (!buttonPress1) {
                quit.delete();
                pressEsc.draw();
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e ) {
            }
            if (!buttonPress) {
                pressSpace.delete();
                start.draw();
            }
            if (!buttonPress1 && !buttonReleased) {
                pressEsc.delete();
                quit.draw();
            }
            try {
                Thread.sleep(800);
            } catch (InterruptedException e ) {
            }
        }
    }

    public void gameOver() {
        bg1.draw();
        p1win.draw();
        /*if(p1wins == true){
            p1win.draw();
        }
        if(p2wins == true){
            p1win.draw();
        }*/
        gameOver.draw();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e ) {
        }
        gameOver.delete();
        p1win.delete();
        bg1.delete();
    }

    public void keyboard(){
        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent startGamePressed = new KeyboardEvent();
        startGamePressed.setKey(KeyboardEvent.KEY_SPACE);
        startGamePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(startGamePressed);

        KeyboardEvent startGame = new KeyboardEvent();
        startGame.setKey(KeyboardEvent.KEY_SPACE);
        startGame.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(startGame);

        KeyboardEvent quitGamePressed = new KeyboardEvent();
        quitGamePressed.setKey(KeyboardEvent.KEY_ESC);
        quitGamePressed.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(quitGamePressed);

        KeyboardEvent quitGame = new KeyboardEvent();
        quitGame.setKey(KeyboardEvent.KEY_ESC);
        quitGame.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(quitGame);
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart() {
        gameStart = false;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            if (!gameStart) {
                buttonPress = true;
                pressSpace.delete();
                button.delete();
                start.delete();
                buttonPressed.draw();
                start.draw();
            }
        } else if (e.getKey() == KeyboardEvent.KEY_ESC){
            if (!gameStart) {
                buttonPress1 = true;
                pressEsc.delete();
                button1.delete();
                quit.delete();
                buttonPressed1.draw();
                quit.draw();
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            if (!gameStart) {
                buttonPress = false;
                gameStart = true;
                buttonReleased = true;
                title.delete();
                pressEsc.delete();
                button.delete();
                button1.delete();
                buttonPressed.delete();
                buttonPressed1.delete();
                start.delete();
                quit.delete();
                bg.delete();
            }
        } else if (e.getKey() == KeyboardEvent.KEY_ESC){
            System.exit(0);
        }
    }
}
