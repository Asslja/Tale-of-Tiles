package com.example.game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameBoard {
    private Tile[][] gameBoard;
    /**
     * constructs a new gameboard. This gameboard consists of a 2D Tile Array where the fields are generated
     * pseudo-randomly. It is a 2D Array to have easier access.
     * Every gameboard also needs a player.
     * The visual representation happens in class "Draw".
     *
     * IMPORTANT: gameBoard is [height][width] so [y][x] and not [x][y]
     */
    public GameBoard(int fieldHeight, int fieldWidth){
        gameBoard = new Tile[fieldHeight][fieldWidth];
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (j == 0 || j == fieldWidth -1){
                    gameBoard[i][j] = new Tile(Tile.TileType.GRAY);
                }
                else gameBoard[i][j] = new Tile(Tile.TileType.getRandomTileType());
            }
        }
    }

    public void makeVisitedTileGray(int x, int y){
        gameBoard[y][x] = new Tile(Tile.TileType.GRAY);
    }

    public Tile[][] getGameBoard(){
        return gameBoard;
    }
}
