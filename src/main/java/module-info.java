module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.game to javafx.fxml;
    exports com.example.game;
    exports com.example.game.event;
    opens com.example.game.event to javafx.fxml;
}