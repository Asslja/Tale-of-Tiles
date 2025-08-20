package com.example.game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameBoard {
    private final int tileWidth = 80; //how big is a tile
    private final int fieldHeight = 5;//how many tiles in height. this should be uneven so the starting point doesn't look awkward
    private final int fieldWidth = 10;//how many tiles in width

    private Tile[][] gameBoard;
    private Player player;
    private Draw draw;
    /**
     * constructs a new gameboard. This gameboard consists of a 2D Tile Array where the fields are generated
     * pseudo-randomly. It is a 2D Array to have easier access.
     * Every gameboard also needs a player.
     * The visual representation happens in class "Draw".
     */
    public GameBoard(){
        gameBoard = new Tile[fieldHeight][fieldWidth];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (j == 0 || j == fieldWidth -1){
                    gameBoard[i][j] = new Tile(Tile.TileType.GRAY);
                }
                else gameBoard[i][j] = new Tile(Tile.TileType.getRandomTileType());
            }
        }
        player = new Player(fieldHeight, fieldWidth, tileWidth);
        Draw.DrawGameBoard(gameBoard, player, tileWidth, this);
    }

    public void MoveDirection(KeyEvent key){
        switch(key.getCode()){
            case UP , W -> player.moveUp();
            case DOWN, S -> player.moveDown();
            case RIGHT, D -> player.moveRight();
            case LEFT, A -> player.moveLeft();
        }
        System.out.println(player.getPosx() + ", " + player.getPosy());
        //draw.change(player.getPosx(), player.getPosy());
    }
}
