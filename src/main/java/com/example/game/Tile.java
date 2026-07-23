package com.example.game;

import com.example.game.event.*;

import java.util.Random;

/**
 * this class saves the different colors (and functions) a tile can have
 * not sure yet if it really needs to extend stackpane (depends on where the player position will be saved
 */
public class Tile {
    public TileType type;//this tile

    /**
     * constructs a new tile with a certain type
     * @param type tile type (they're named by colour)
     */
    public Tile(TileType type){
        this.type = type;
    }

    /**
     * the different kinds of tiles that are possible (different types)
     * it also stores fitting rgb values for colouring the tiles in the playing field
     */
    public static enum TileType {
        GREEN(new int[]{113, 198, 96}, new GreenEvent()),
        RED(new int[]{200, 0, 0}, new RedEvent()),
        BLUE(new int[]{64, 136, 206}, new BlueEvent()),
        YELLOW(new int[]{255, 235, 129}, new YellowEvent()),
        PURPLE(new int[]{164, 145, 206}, new PurpleEvent()),
        PINK(new int[]{255, 191, 246}, new PinkEvent()),
        ORANGE(new int[]{255, 180, 96}, new OrangeEvent()),
        GRAY(new int[]{220, 220, 220}, new GrayEvent());//nothing happens (this is also the color of "start")

        public final int[] rgb;
        public final TileEvent tileEvent;

        TileType(int[] rgb, TileEvent tileEvent) {
            this.rgb = rgb;
            this.tileEvent = tileEvent;
        }

        /**
         * selects a random value of enum TileType
         * @return a pseudo-randomly generated entry of tileType
         */
        public static TileType getRandomTileType(){
            TileType[] types = TileType.values();
            Random rnd = new Random();
            int out = rnd.nextInt(types.length-1);
            return types[out];
        }
    }
}
