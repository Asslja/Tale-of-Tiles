package com.example.game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameApplication {
    private static GameBoard gameBoard;

    public static void main(String[] args) {
        gameBoard = new GameBoard();
    }
}