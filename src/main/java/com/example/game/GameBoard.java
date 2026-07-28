package com.example.game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameBoard {
    private Tile[][] gameBoard;
    private byte[][] barriers;//1 means there is a barrier, 0 means there isn't

    /**
     * constructs a new gameboard. This gameboard consists of a 2D Tile Array where the fields are generated
     * pseudo-randomly. It is a 2D Array to have easier access.
     * every gameboard has an underlying 2D byte array to check whether there is a barrier on a field
     * Every gameboard also needs a player.
     * The visual representation happens in class "Draw".
     *
     * IMPORTANT: gameBoard and barriers is [height][width] so [y][x] and not [x][y]
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

        barriers = new byte[fieldHeight][fieldWidth];//same as gameBoard
    }

    public void makeVisitedTileGray(int x, int y){
        gameBoard[y][x] = new Tile(Tile.TileType.GRAY);
    }

    public void executeEvent(Player player){
        gameBoard[player.getPosy()][player.getPosx()].type.tileEvent.execute(player, this);
    }

    public Tile[][] getGameBoard(){
        return gameBoard;
    }

    public byte[][] getBarriers() {
        return barriers;
    }
}
