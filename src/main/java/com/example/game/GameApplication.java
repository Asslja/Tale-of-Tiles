package com.example.game;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameApplication {

    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
    }

    /**public void keyPressed(KeyEvent key){
        if (key.getCode() == KeyCode.LEFT){
            player.moveLeft();
        } else if (key.getCode() == KeyCode.RIGHT) {
            player.moveRight();
        } else if (key.getCode() == KeyCode.UP) {
            player.moveUp();
        } else if (key.getCode() == KeyCode.DOWN) {
            player.moveDown();
        }
    }*/

}