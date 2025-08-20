package com.example.game;

import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;

/**
 * this class is where our player stats are saved
 * mainly his remaining lives and his current position (texture will probably be saved in Draw)
 */
public class Player {
    private int lives;
    private int posx;
    private int posy;
    private final int maxHeight;//these are saved so the player can't move out of the playing field
    private final int maxWidth;
    private final int tileWidth;

    /**
     * constructs a new player and initializes its values
     * @param maxHeight the number of tiles the playing field has in height
     * @param maxWidth the number of tiles in height
     */
    public Player(int maxHeight, int maxWidth, int tileWidth){
        lives = 4;
        posx = 0;
        posy = maxHeight/2;
        this.tileWidth = tileWidth;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
    }

    public int getPosx(){
        return posx;
    }
    public int getPosy(){
        return posy;
    }

    public void moveLeft(){
        if (posx > 0){
            posx--;//don't go outside
        }
    }
    public void moveRight(){
        if (posx < maxWidth){
            posx++;
        }
    }
    public void moveUp(){
        if (posy > 0){
            posy--;
        }
    }
    public void moveDown(){
        if (posy < maxHeight){
            posy++;
        }
    }
}
