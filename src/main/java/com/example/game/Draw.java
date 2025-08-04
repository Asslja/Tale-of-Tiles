package com.example.game;

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
    private final int tileWidth = 80;//how big is a tile
    private GridPane layout;
    private Scene scene;
    private Stage stage;

    private static Tile[][] gameBoard;
    private static Player player;

    public Draw(){}

    /**
     * this method saves the gameBoard and player dates for the visual representation of the start of the game
     * @param board the game board
     * @param p the player
     */
    public static void DrawGameBoard(Tile[][] board, Player p){
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
        layout = new GridPane();//using gridpane is the easiest since we can select the tiles later by choosing row and column
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
                int[] rgb = gameBoard[i][j].type.rgb;
                tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
                layout.add(tileShape, j, i);
            }
        }
        Circle playerShape = new Circle(tileWidth /2, Color.BLACK);
        layout.add(playerShape, player.getPosx(), player.getPosy());
        scene = new Scene(layout, gameBoard[0].length * tileWidth, gameBoard.length * tileWidth);
        stage.setScene(scene);
        stage.show();
    }
}
