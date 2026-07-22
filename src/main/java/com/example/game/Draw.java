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
 */
public class Draw extends Application{
    private static Draw instance;//this makes sure that there is always only one Draw object
    private GridPane layout;
    private Scene scene;
    private Stage stage;

    private static GameBoard gameBoard;
    private static Player player;
    private static int tileWidth;//how big is a tile

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
        for (int i = 0; i < gameBoard.getGameBoard().length; i++) {
            for (int j = 0; j < gameBoard.getGameBoard()[i].length; j++) {
                Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
                int[] rgb = gameBoard.getGameBoard()[i][j].type.rgb;
                tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
                layout.add(tileShape, j, i);
            }
        }
        //draw another row for "lives" and add lives
        int lives = player.getLives();
        for (int j = 0; j < gameBoard.getGameBoard()[0].length; j++) {
            Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
            int[] rgb = Tile.TileType.GRAY.rgb;
            tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
            layout.add(tileShape, j, gameBoard.getGameBoard().length);

            if (j>0 && lives>0){
                Circle liveShape = new Circle(tileWidth/4, Color.RED);
                liveShape.setStroke(Color.BLACK);
                liveShape.setStrokeWidth(2);
                layout.add(liveShape, j, gameBoard.getGameBoard().length);
                lives--;
            }
        }

        Circle playerShape = new Circle(tileWidth /2, Color.BLACK);
        layout.add(playerShape, player.getPosx(), player.getPosy());
        scene = new Scene(layout, gameBoard.getGameBoard()[0].length * tileWidth, (gameBoard.getGameBoard().length * tileWidth)+tileWidth);//+1 tile for lives

        scene.setOnKeyPressed(event -> {
            if (!gameOver){
                player.move(event);
            }
        });//when key pressed -> move direction

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                if (player.hasPosChanged()) {
                    player.changesMade();
                    redraw(player.getPosx(), player.getPosy());
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
    public Scene getScene(){return scene;}

    /**
     * redraws the player figure ate its current position and changes the previously visited
     * tile to gray. Afterwards its function and color are GRAY which means it will be seen
     * as gray in color and if the player steps on it again, no game action will follow
     * @param posx current pos of the player
     * @param posy
     */
    public void redraw(int posx, int posy){
        Circle playerShape = new Circle(tileWidth /2, Color.BLACK);
        layout.add(playerShape, posx, posy);
        
        int prevx = posx, prevy = posy;        
        switch (player.getLastMove()){
            case 'U' -> prevy = prevy+1;
            case 'D' -> prevy = prevy-1;
            case 'R' -> prevx = prevx-1;
            case 'L' -> prevx = prevx+1;
        }
        gameBoard.makeVisitedTileGray(prevx, prevy);

        Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
        int[] rgb = gameBoard.getGameBoard()[prevy][prevx].type.rgb;
        tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
        layout.add(tileShape, prevx, prevy);
    }
}
