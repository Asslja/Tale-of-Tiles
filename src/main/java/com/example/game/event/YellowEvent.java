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
        gameBoard.removeBarrier(player.getPosx()+1, player.getPosy());//right in front of the player
        if(player.getPosy()!=0){ gameBoard.removeBarrier(player.getPosx()+1, player.getPosy()-1);}
        if(player.getPosy()!=gameBoard.height-1){ gameBoard.removeBarrier(player.getPosx()+1, player.getPosy()+1);}
    }
}
