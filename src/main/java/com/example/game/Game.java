package com.example.game;

import javafx.animation.AnimationTimer;

public class Game {
    private final int tileWidth = 80; //how big is a tile
    private final int fieldHeight = 5;//how many tiles in height. this should be uneven so the starting point is in the middle
    private final int fieldWidth = 10;//how many tiles in width
    private final int lives = 3;

    private Draw draw;
    private Player player;

    public Game() {
        GameBoard gameBoard = new GameBoard(fieldHeight, fieldWidth);
        player = new Player(fieldHeight, fieldWidth, tileWidth, lives);
        Draw.DrawGameBoard(player, tileWidth, gameBoard);
        draw = Draw.getInstance();
    }

    public void startGameLoop() {

    }
}
