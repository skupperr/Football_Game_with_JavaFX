package com.example.opteam;


import Network.ClientTest;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HelloApplication extends Application {

    private static final CountDownLatch connectionLatch = new CountDownLatch(1);
    private static GamePanel sharedGamePanel;

    @Override
    public void start(Stage primaryStage) {
        if (sharedGamePanel == null) {
            sharedGamePanel = new GamePanel();
        }

        Pane root = new Pane();
        Platform.runLater(() -> root.getChildren().add(sharedGamePanel.getCanvas()));

        Scene scene = new Scene(root, sharedGamePanel.getScreenWidth(), sharedGamePanel.getScreenHeight());
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();

        Platform.runLater(() -> sharedGamePanel.startGameLoop());

        // Schedule game termination after 5 minutes
        scheduleGameStop(primaryStage);
    }

    private void scheduleGameStop(Stage stage) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            Platform.runLater(() -> {
                sharedGamePanel.stopGameLoop(); // Stop the AnimationTimer
                stage.close(); // Close the JavaFX stage
                System.out.println("Game stopped after 5 minutes.");
            });
            scheduler.shutdown();
        }, 5, TimeUnit.MINUTES); // 5-minute delay
    }

    public static void main(String[] args) {
        boolean isServer = false;

        if (!isServer) {
            sharedGamePanel = new GamePanel();

            new Thread(() -> {
                ClientTest clientTest = new ClientTest(22222, connectionLatch);
                clientTest.startClient(sharedGamePanel);
            }).start();

            try {
                connectionLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        launch(args);
    }
}
