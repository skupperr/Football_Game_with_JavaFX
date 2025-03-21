package com.example.loginpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setTitle("");
        stage.setResizable(false);
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();

//        MusicManager.playBackgroundMusic();
    }

    public static void main(String[] args) {
        launch();
    }
}