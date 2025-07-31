package com.example.game;

import javafx.scene.layout.StackPane;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        GREEN(new int[]{113, 198, 96}),
        RED(new int[]{200, 0, 0}),
        BLUE(new int[]{64, 136, 206}),
        YELLOW(new int[]{255, 235, 129}),
        PURPLE(new int[]{164, 145, 206}),
        PINK(new int[]{255, 191, 246}),
        ORANGE(new int[]{255, 180, 96}),
        GRAY(new int[]{220, 220, 220});//nothing happens (this is also the color of "start")

        public final int[] rgb;

        TileType(int[] rgb) {
            this.rgb = rgb;
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
