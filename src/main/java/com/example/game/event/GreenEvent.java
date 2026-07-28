package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

public class GreenEvent implements TileEvent {

    /**
     * grants the player one additional live
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        player.changeLivesByFactor(1);
    }
}
