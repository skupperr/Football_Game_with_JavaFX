module com.example.game_basic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.opteam to javafx.fxml;
    exports com.example.opteam;
}