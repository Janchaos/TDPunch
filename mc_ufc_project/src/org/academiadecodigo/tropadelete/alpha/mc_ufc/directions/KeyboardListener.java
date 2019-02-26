package org.academiadecodigo.tropadelete.alpha.mc_ufc.directions;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tropadelete.alpha.mc_ufc.Game;


public class KeyboardListener implements KeyboardHandler {

    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;

        Keyboard keyboard = new Keyboard(this);

        KeyboardEvent keyboardLeftArrow = new KeyboardEvent();
        keyboardLeftArrow.setKey(KeyboardEvent.KEY_LEFT);
        keyboardLeftArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(keyboardLeftArrow);

        KeyboardEvent keyboardRightArrow = new KeyboardEvent();
        keyboardRightArrow.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardRightArrow.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(keyboardRightArrow);

        KeyboardEvent keyboardZbuttonPress = new KeyboardEvent();
        keyboardZbuttonPress.setKey(KeyboardEvent.KEY_Z);
        keyboardZbuttonPress.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(keyboardZbuttonPress);

        KeyboardEvent keyboardZbuttonRelease = new KeyboardEvent();
        keyboardZbuttonRelease.setKey(KeyboardEvent.KEY_Z);
        keyboardZbuttonRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        keyboard.addEventListener(keyboardZbuttonRelease);


    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                game.setDirections(Directions.LEFT);
                break;
            case KeyboardEvent.KEY_RIGHT:
                game.setDirections(Directions.RIGHT);
                break;
            case KeyboardEvent.KEY_Z:
                game.punch();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_Z:
                game.resetPunch();
        }
    }
}
