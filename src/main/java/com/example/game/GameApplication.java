package com.example.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;

public class GameApplication extends Application {
    private final int tileWidth = 80;//how big is a tile
    private final int fieldHeight = 5;//how many tiles in height. this should be uneven so the starting point doesn't look awkward
    private final int fieldWidth = 10;//how many tiles in width

    private Player player;
    private Scene scene;
    private GridPane layout;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Tale of Tiles");
        layout = new GridPane();//using gridpane is the easiest since we can select the tiles later by choosing row and column

        //the layout is made up of many rectangles (like a table)
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                Rectangle tileShape = new Rectangle(tileWidth, tileWidth);
                if (j == 0 || j == fieldWidth -1){
                    Tile tileType = new Tile(Tile.TileType.GRAY);
                    int[] rgb = tileType.type.rgb;
                    tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
                }
                else {
                    Tile tileType = new Tile(Tile.TileType.getRandomTileType());
                    int[] rgb = tileType.type.rgb;
                    tileShape.setFill(Color.rgb(rgb[0], rgb[1], rgb[2]));
                }
                layout.add(tileShape, j, i);
            }
        }
        //the player shall also appear in our starting window
        player = new Player(fieldHeight, fieldWidth);
        Circle playerShape = new Circle(tileWidth /2, Color.BLACK);
        layout.add(playerShape, player.getPosx(), player.getPosy());

        scene = new Scene(layout, fieldWidth * tileWidth, fieldHeight * tileWidth);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void keyPressed(KeyEvent key){
        if (key.getCode() == KeyCode.LEFT){
            player.moveLeft();
        } else if (key.getCode() == KeyCode.RIGHT) {
            player.moveRight();
        } else if (key.getCode() == KeyCode.UP) {
            player.moveUp();
        } else if (key.getCode() == KeyCode.DOWN) {
            player.moveDown();
        }
    }

}