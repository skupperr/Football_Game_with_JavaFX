package com.example.game_basic;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyHandler {

    public boolean upPressed, downPressed, leftPressed, rightPressed, UpArrowPressed;
    public boolean kickPressed;


    public void handleKeyPressed(KeyEvent event) {
        KeyCode code = event.getCode();
//        System.out.println(code);
        if (code == KeyCode.W) {

            upPressed = true;
        }
        if (code == KeyCode.S) {
            downPressed = true;
        }
        if (code == KeyCode.A) {
            leftPressed = true;
        }
        if (code == KeyCode.D) {
            rightPressed = true;
        }
        if (code == KeyCode.SPACE) {
            kickPressed = true; // Set to true on key press
        }
        if (code == KeyCode.UP) {
            UpArrowPressed = true; // Set to true on key press
        }

    }

    public void handleKeyReleased(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.W) {
            upPressed = false;
        }
        if (code == KeyCode.S) {
            downPressed = false;
        }
        if (code == KeyCode.A) {
            leftPressed = false;
        }
        if (code == KeyCode.D) {
            rightPressed = false;
        }
        if (code == KeyCode.SPACE) {
            kickPressed = false;
        }
        if (code == KeyCode.UP) {
            UpArrowPressed = false; // Set to true on key press
        }

    }
}

//public class KeyHandler {
//
//    public boolean upPressed, downPressed, leftPressed, rightPressed;
//
//    public void handleKeyPressed(KeyEvent event) {
//        KeyCode code = event.getCode();
//        if (code == KeyCode.W) {
//
//            upPressed = true;
//        }
//        if (code == KeyCode.S) {
//            downPressed = true;
//        }
//        if (code == KeyCode.A) {
//            leftPressed = true;
//        }
//        if (code == KeyCode.D) {
//            rightPressed = true;
//        }
//    }
//
//    public void handleKeyReleased(KeyEvent event) {
//        KeyCode code = event.getCode();
//        if (code == KeyCode.W) {
//            upPressed = false;
//        }
//        if (code == KeyCode.S) {
//            downPressed = false;
//        }
//        if (code == KeyCode.A) {
//            leftPressed = false;
//        }
//        if (code == KeyCode.D) {
//            rightPressed = false;
//        }
//    }
//}
