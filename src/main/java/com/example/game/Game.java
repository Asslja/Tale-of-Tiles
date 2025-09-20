package com.example.game;

public class Game{
    private final int tileWidth = 80; //how big is a tile
    private final int fieldHeight = 5;//how many tiles in height. this should be uneven so the starting point doesn't look awkward
    private final int fieldWidth = 10;//how many tiles in width

    public Game(){
        GameBoard gameBoard = new GameBoard(fieldHeight, fieldWidth);
        Player player = new Player(fieldHeight, fieldWidth, tileWidth);
        Draw.DrawGameBoard(gameBoard.gameBoard, player, tileWidth, gameBoard);
    }

    public void start(){

    }
}
