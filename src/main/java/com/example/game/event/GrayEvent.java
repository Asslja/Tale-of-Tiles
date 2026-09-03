package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

public class GrayEvent implements TileEvent{

    /**
     * in a gray event/on a gray tile nothing happens
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {

    }
}
