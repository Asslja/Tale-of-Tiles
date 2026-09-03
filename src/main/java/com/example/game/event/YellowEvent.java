package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

public class YellowEvent implements TileEvent{

    /**
     * removes the barriers from the three tiles in front of the player if there are any. One barrier in front (=right)
     * of the player and the ones "above" and "beneath" that one
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        byte[][] barriers = gameBoard.getBarriers();
        barriers[player.getPosy()][player.getPosx()+1] = 0;//right in front of the player
        barriers[player.getPosy()-1][player.getPosx()+1] = 0;
        barriers[player.getPosy()+1][player.getPosx()+1] = 0;
    }
}
