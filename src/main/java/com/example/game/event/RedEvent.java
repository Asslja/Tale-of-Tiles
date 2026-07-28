package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

public class RedEvent implements TileEvent{
    /**
     * subtracts one of the players lives
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        player.changeLivesByFactor(-1);
    }
}
