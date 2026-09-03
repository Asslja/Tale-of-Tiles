package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

import java.util.Random;

public class OrangeEvent implements TileEvent{

    /**
     * teleports the player to a random tile that is farther away from the goal
     * if there is a barrier at the tile the player gets teleported to. the barrier gets removed
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        Random random = new Random();
        int newX = random.nextInt(0, player.getPosx());
        int newY = random.nextInt(0, gameBoard.getGameBoard().length);

        if (gameBoard.isBlockedByBarrier(newX, newY)) gameBoard.removeBarrier(newX, newY);

        player.teleport(newX, newY);
        gameBoard.makeVisitedTileGray(newX, newY);
    }
}
