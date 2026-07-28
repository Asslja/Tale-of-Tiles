package com.example.game.event;

import com.example.game.GameBoard;
import com.example.game.Player;

import java.util.Random;

public class PinkEvent implements TileEvent{

    /**
     * adds barriers to two tiles in front of the player. One barrier directly in front (=right)
     * to the player and one either above or beneath that one. the additional tile gets chosen randomly
     * @param player
     * @param gameBoard
     */
    @Override
    public void execute(Player player, GameBoard gameBoard) {
        char[] b = new char[2];
        b[0] = 'U';//up
        b[1] = 'D';//down
        Random rnd = new Random();
        int additionalBarrier = rnd.nextInt(b.length);

        byte[][] barriers = gameBoard.getBarriers();
        barriers[player.getPosy()][player.getPosx()+1] = 1;//right in front of the player

        if (additionalBarrier==0){
            if (player.getPosy()!=0) {//barrier can't be outside of gameboard
                barriers[player.getPosy() - 1][player.getPosx() + 1] = 1;
            }
        } else {
            if (player.getPosy()!=gameBoard.getGameBoard().length-1) {
                barriers[player.getPosy() + 1][player.getPosx() + 1] = 1;
            }
        }
    }
}
