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
        for (int i = 0; i < gameBoard.height; i++) {
            for (int j = 1; j < gameBoard.width-1; j++) {
                gameBoard.changeTile(j, i);
            }
        }
        gameBoard.makeVisitedTileGray(player.getPosx(), player.getPosy());
    }
}
