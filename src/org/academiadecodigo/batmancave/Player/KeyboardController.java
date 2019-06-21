package org.academiadecodigo.batmancave.Player;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardController implements KeyboardHandler {

    private Player player;
    private Keyboard keyboard;

    public KeyboardController(Player player) {
        this.player = player;
        keyboard = new Keyboard(this);
    }

    public void addEventPressKey (int key) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(event);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        int key = keyboardEvent.getKey();

        player.keyAction(key);

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


}
