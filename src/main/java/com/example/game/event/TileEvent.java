package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

/**
 * a TileEvent happens whenever the player steps on a tile. depending on its color different events happen
 * this might update the players lives, the player position, colors in the gameboard or barriers in the gameboard
 */
public interface TileEvent {

    /**
     *
     * @param player
     */
    public void execute(Player player, GameBoard gameBoard);
}
