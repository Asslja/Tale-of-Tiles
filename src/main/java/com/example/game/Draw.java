package com.example.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * in this class everything visual will be handled, so everything that will appear on the screen
 * this class does not change the gameboard or the player but only the representation of their attributes!
 */
public class Draw extends Application{
    private static Draw instance;//this makes sure that there is always only one Draw object
    private GridPane layout;
    private Scene scene;
    private Stage stage;

    private static GameBoard gameBoard;
    private static Player player;
    private static int tileWidth;//how big is a tile

    //entities that are shown on the gameboard
    private Rectangle[][] tileShapes;
    private Rectangle[][] barrierShapes;
    private Circle playerShape;
    private Rectangle[] lifeTiles;
    private Circle[] lifeShapes;


    boolean gameOver = false;//determines whether the game is over (to stop keyEvent)

    public Draw(){
        instance = this;
    }

    /**
     * this method saves the gameBoard and player dates for the visual representation of the start of the game
     * @param p the player
     */
    public static void DrawGameBoard(Player p, int tileW, GameBoard board){
        tileWidth = tileW;
        gameBoard = board;
        player = p;
        launch();
    }

    /**
     * this method builds the first notion of gameBoard (starting point or start of the game)
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    public void start(Stage stage) throws Exception{
        this.stage = stage;
        stage.setTitle("Tale of Tiles");
        layout = new GridPane();//using gridpane is the easiest since we can select the tiles later by choosing row and column (it's also immediately in the right shape)

        initializeGameBoardAndBarriers();
        initializeLives();
        initializePlayer();

        scene = new Scene(layout, gameBoard.getGameBoard()[0].length * tileWidth, (gameBoard.getGameBoard().length * tileWidth)+tileWidth);//+1 tile for lives

        scene.setOnKeyPressed(event -> {
            if (!gameOver){
                player.move(event, gameBoard.getBarriers());
            }
        });//when key pressed -> move direction

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                if (player.hasPosChanged()) {
                    player.changesMade();
                    redrawAfterMove(player.getPosx(), player.getPosy());
                    gameBoard.executeEvent(player);
                    redrawAfterEvent();
                }
                if (player.getLives()<=0){
                    gameOver = true;
                    stop();
                    System.out.println("Game Lost!");
                }
                if (player.getPosx() >= gameBoard.getGameBoard()[0].length-1) {
                    gameOver = true;
                    stop();
                    System.out.println("Game Won!");
                }
            }
        };
        timer.start();

        stage.setScene(scene);
        stage.show();
    }

    public static Draw getInstance(){return instance;}

    /**
     * redraws the player figure ate its current position and changes the previously visited
     * tile to gray. Afterwards its function and color are GRAY which means it will be seen
     * as gray in color and if the player steps on it again, no game action will follow
     * @param posx current pos of the player
     * @param posy
     */
    public void redrawAfterMove(int posx, int posy){
        updatePlayer();
        
        int[] prevPos = player.getPrevPos();
        gameBoard.makeVisitedTileGray(prevPos[0], prevPos[1]);

        int[] rgb = gameBoard.getGameBoard()[prevPos[1]][prevPos[0]].type.rgb;
        tileShapes[prevPos[1]][prevPos[0]].setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
    }

    /**
     * updates any visual representation that has been changed after the event has been executed
     * such as lives, position, color, barriers
     */
    public void redrawAfterEvent(){
        updateGameBoardAndBarriers();
        updatePlayer();
        updateLives();
    }

    /**
     * initializes, draws ands adds all the tiles and barriers while also managing their visibility
     */
    private void initializeGameBoardAndBarriers(){
        //set new shapes for the entities on our drawn gameboard
        tileShapes = new Rectangle[gameBoard.getGameBoard().length][gameBoard.getGameBoard()[0].length];
        barrierShapes = new Rectangle[gameBoard.getGameBoard().length][gameBoard.getGameBoard()[0].length];

        for (int i = 0; i < gameBoard.getGameBoard().length; i++) {
            for (int j = 0; j < gameBoard.getGameBoard()[i].length; j++) {
                Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
                int[] rgb = gameBoard.getGameBoard()[i][j].type.rgb;
                tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));

                tileShapes[i][j] = tileShape;
                layout.add(tileShape, j, i);

                //set in every tile a barrier and set its visibility accordingly. this won't disturb the game logic
                Rectangle barrierShape = new Rectangle(tileWidth/5, tileWidth, Color.BLACK);
                barrierShapes[i][j] = barrierShape;
                barrierShape.setVisible(gameBoard.getBarriers()[i][j] == 1);//only visible if there is actually a barrier
                layout.add(barrierShape, j, i);
            }
        }
    }

    /**
     * recolors the tiles and resets the visibility of the barriers
     */
    private void updateGameBoardAndBarriers(){
        for (int i = 0; i < gameBoard.getGameBoard().length; i++) {
            for (int j = 0; j < gameBoard.getGameBoard()[i].length; j++) {
                int[] rgb = gameBoard.getGameBoard()[i][j].type.rgb;
                tileShapes[i][j].setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));

                barrierShapes[i][j].setVisible(gameBoard.getBarriers()[i][j] == 1);//only visible if there is actually a barrier
            }
        }
    }

    /**
     * creates a new player for the draw object
     */
    private void initializePlayer(){
        playerShape = new Circle(tileWidth /2, Color.BLACK);
        layout.add(playerShape, player.getPosx(), player.getPosy());
    }

    /**
     * changes the player to its current position
     */
    private void updatePlayer(){
        GridPane.setColumnIndex(playerShape, player.getPosx());
        GridPane.setRowIndex(playerShape, player.getPosy());
    }

    /**
     * draws another row for "lives" under the gameboard and adds lives
     */
    private void initializeLives(){
        lifeTiles = new Rectangle[gameBoard.getGameBoard()[0].length];
        lifeShapes = new Circle[player.maxLives];

        int lives = player.getLives();
        for (int i = 0; i < gameBoard.getGameBoard()[0].length; i++) {
            Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
            int[] rgb = Tile.TileType.GRAY.rgb;
            tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));

            lifeTiles[i] = tileShape;
            layout.add(tileShape, i, gameBoard.getGameBoard().length);

            if (i>0 && player.maxLives>=i){
                Circle lifeShape = new Circle(tileWidth/4, Color.RED);
                lifeShape.setStroke(Color.BLACK);
                lifeShape.setStrokeWidth(2);

                lifeShape.setVisible(lives>0);//make only the lives visible that the player currently has
                lifeShapes[i-1] = lifeShape;
                layout.add(lifeShape, i, gameBoard.getGameBoard().length);
                lives--;
            }
        }
    }

    /**
     * updates the representation of how many lives the player currently has
     */
    private void updateLives(){
        int lives = player.getLives();
        for (int i = 0; i < lifeShapes.length; i++) {
            lifeShapes[i].setVisible(lives>0);
            lives--;
        }
    }
}
