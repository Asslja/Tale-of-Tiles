package com.example.game;

import javafx.scene.input.KeyEvent;

/**
 * this class is where our player stats are saved
 * mainly his remaining lives and his current position (texture will probably be saved in Draw)
 */
public class Player {
    private int lives;
    public final int maxLives;//player cannot have more lives than this (because they can't be shown visually)

    private int posx;
    private int posy;
    private final int maxHeight;//these are saved so the player can't move out of the playing field
    private final int maxWidth;
    private final int tileWidth;

    private boolean posHasChanged;//shows whether the position of the player has changed or not
    private int[] prevPos;//saves the previous position as {x}{y}
    private boolean shield;

    /**
     * constructs a new player and initializes its values
     * @param maxHeight the number of tiles the playing field has in height
     * @param maxWidth the number of tiles in width (has to be bigger than 4 = nr of lives)
     * @param tileWidth
     * @param lives how many lives the player has at the start of the game
     */
    public Player(int maxHeight, int maxWidth, int tileWidth, int lives, int maxLives){
        this.lives = lives;//has to be smaller than width
        this.maxLives = maxLives;
        posx = 0;
        posy = maxHeight/2;
        this.tileWidth = tileWidth;
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        posHasChanged = false;
        shield = false;
    }

    /**
     *changes the position of the player after a key has been pressed on the keyboard
     * if there isn't a barrier in the way
     * player cannot move outside the gameBoard
     * @param key the key that has been pressed to trigger this action
     */
    public void move(KeyEvent key, GameBoard gameBoard){
        prevPos = new int[2];
        prevPos[0] = posx;
        prevPos[1] = posy;

        switch(key.getCode()){
            case UP , W -> {if (posy > 0){
                if (!gameBoard.isBlockedByBarrier(posx, posy-1)) {
                    posy--;
                    posHasChanged = true;
                }
            }}//if-statement so the player doesn't go outside the field
            case DOWN, S -> {if (posy < maxHeight-1){
                if (!gameBoard.isBlockedByBarrier(posx, posy+1)) {
                    posy++;
                    posHasChanged = true;
                }
            }}
            case RIGHT, D -> {if (posx < maxWidth-1){
                if (!gameBoard.isBlockedByBarrier(posx+1, posy)) {
                    posx++;
                    posHasChanged = true;
                }
            }}
            case LEFT, A -> {if (posx > 0){
                if (!gameBoard.isBlockedByBarrier(posx-1, posy)) {
                    posx--;
                    posHasChanged = true;
                }
            }}
        }
    }

    public void teleport(int newX, int newY){
        prevPos = new int[2];
        prevPos[0] = posx;
        prevPos[1] = posy;

        posx = newX;
        posy = newY;

        posHasChanged = true;
    }

    /**
     * changes the nr of lives according to a negative or positive factor
     * @param factor e.g. +1 or -1 -> adds or subtracts a life
     */
    public void changeLivesByFactor(int factor){
        if (lives >= maxLives && factor >0) return; //if there are already the max nr of lives we can't add more
        lives = lives + factor;
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

    public int[] getPrevPos(){
        return prevPos;
    }

    public int getLives(){
        return lives;
    }

    public void addShield(){
        shield = true;
    }

    public void removeShield(){
        shield = false;
    }

    public boolean hasShield(){
        return shield;
    }
}
