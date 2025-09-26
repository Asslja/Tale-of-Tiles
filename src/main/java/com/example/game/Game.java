package com.example.game;

import javafx.animation.AnimationTimer;

public class Game {
    private final int tileWidth = 80; //how big is a tile
    private final int fieldHeight = 5;//how many tiles in height. this should be uneven so the starting point doesn't look awkward
    private final int fieldWidth = 10;//how many tiles in width
    private Draw draw;
    private Player player;

    public Game() {
        GameBoard gameBoard = new GameBoard(fieldHeight, fieldWidth);
        player = new Player(fieldHeight, fieldWidth, tileWidth);
        Draw.DrawGameBoard(player, tileWidth, gameBoard);
        draw = Draw.getInstance();
    }

    public void startGameLoop() {
        //draw.getScene().setOnKeyPressed(event -> player.move(event));//when key pressed -> move direction
//        AnimationTimer timer = new AnimationTimer() {
//            public void handle(long now) {
//                if (player.hasPosChanged()) {
//                    System.out.println("true");
//                    player.changesMade();
//                    draw.redraw(player.getPosx(), player.getPosy());
//                }
//                if (player.getPosx() >= fieldWidth) {
//                    stop();
//                    System.out.println("Game Won!");
//                }
//            }
//        };
//        timer.start();
    }
}
