package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

public class BlueEvent implements TileEvent {

    /**
     * produces a shield. whichever tile the player steps on next has no effect (regardless of its color)
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        player.addShield();
    }
}
