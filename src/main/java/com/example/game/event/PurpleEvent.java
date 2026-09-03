package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;
import com.example.game.Tile;

public class PurpleEvent implements TileEvent{

    /**
     * recolors all tiles that are not the start or end tiles to a new random color.
     * the tile where the player is currently positioned turns gray
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        Tile[][] board = gameBoard.getGameBoard();
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length-1; j++) {
                board[i][j] = new Tile(Tile.TileType.getRandomTileType());
            }
        }
        gameBoard.makeVisitedTileGray(player.getPosx(), player.getPosy());
    }
}
