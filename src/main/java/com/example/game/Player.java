package com.example.game;

import javafx.scene.input.KeyEvent;

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

    private boolean posHasChanged;//shows whether the position of the player has changed or not

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
        posHasChanged = false;
    }

    public int getPosx(){
        return posx;
    }
    public int getPosy(){
        return posy;
    }

    /**
     * this method signals if changes in the players position have been made
     * @return whether the player position has changed since last checking
     */
    public boolean hasPosChanged(){return posHasChanged;}
    /**
     * the change has been successfully applied to the visual representation and the variable goes back to its original state to
     * signal future changes
     */
    public void changesMade(){posHasChanged = false;}

    /**
     *changes the position of the player after a key has been pressed on the keyboard
     * @param key the key that has been pressed to trigger this action
     */
    public void move(KeyEvent key){
        switch(key.getCode()){
            case UP , W -> {if (posy > 0){
                posy--;
                posHasChanged = true;
            }}//if-statement so the player doesn't go outside the field
            case DOWN, S -> {if (posy < maxHeight-1){
                posy++;
                posHasChanged = true;
            }}
            case RIGHT, D -> {if (posx < maxWidth-1){
                posx++;
                posHasChanged = true;
            }}
            case LEFT, A -> {if (posx > 0){
                posx--;
                posHasChanged = true;
            }}
        }
        System.out.println(getPosx() + ", " + getPosy());
    }
}
