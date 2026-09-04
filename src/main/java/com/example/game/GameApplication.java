package com.example.game;


public class GameApplication {

    public static void main(String[] args) {
        Game game = new Game();
        game.startGameLoop();

        //for running without warning in terminal:
        //mvn clean javafx:run
    }
}
