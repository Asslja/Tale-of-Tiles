package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

import java.util.Random;

public class OrangeEvent implements TileEvent{

    /**
     * teleports the player to a random tile that is farther away from the goal
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        Random random = new Random();
        int newX = random.nextInt(0, player.getPosx());
        int newY = random.nextInt(0, gameBoard.getGameBoard().length);
        player.teleport(newX, newY);
        gameBoard.makeVisitedTileGray(newX, newY);
    }
}
