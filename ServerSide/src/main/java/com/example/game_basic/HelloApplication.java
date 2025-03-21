package com.example.game_basic;

import Network.ServerClient;
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

    private static String username;

    public static void setUsername(String name) {
        username = name;
    }


//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            System.out.println("Waiting for client to connect...");
//            connectionLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        System.out.println("Client connected! Starting game...");
//
//        Pane root = new Pane();
//        Platform.runLater(() -> root.getChildren().add(sharedGamePanel.getCanvas()));
//
//        Scene scene = new Scene(root, sharedGamePanel.getScreenWidth(), sharedGamePanel.getScreenHeight());
//        primaryStage.setTitle("Server");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.setMaximized(true);
//        primaryStage.show();
//
//        Platform.runLater(() -> sharedGamePanel.startGameLoop());
//
//        // Schedule game termination after 5 minutes
//        scheduleGameStop(primaryStage);
//    }

    @Override
    public void start(Stage primaryStage) {
        // Run client connection in a background thread
        new Thread(() -> {
            try {
                System.out.println("Waiting for client to connect...");
                connectionLatch.await(); // Wait without blocking UI
                Platform.runLater(() -> startGame(primaryStage)); // Update UI safely
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startGame(Stage primaryStage) {
        System.out.println("Client connected! Starting game...");

        Pane root = new Pane();
        root.getChildren().add(sharedGamePanel.getCanvas()); // No need for Platform.runLater()

        Scene scene = new Scene(root, sharedGamePanel.getScreenWidth(), sharedGamePanel.getScreenHeight());
        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();

        sharedGamePanel.startGameLoop(); // Start game loop

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


//    public static void main(String[] args) {
//        boolean isServer = true;
//
//        if (isServer) {
//            sharedGamePanel = new GamePanel();
//
//            new Thread(() -> {
//                try {
//                    ServerClient serverClient = new ServerClient(22222, connectionLatch);
//                    serverClient.waitForClients(sharedGamePanel);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//
//        launch(args);
//    }

    private static boolean launched = false;

    public static void MainCall(){
        if (!launched) {
            launched = true;
            sharedGamePanel = new GamePanel();

            new Thread(() -> {
                try {
                    ServerClient serverClient = new ServerClient(22222, connectionLatch);
                    serverClient.waitForClients(sharedGamePanel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {

        System.out.println("Fuck");

        if (!launched) {
            launched = true;
            sharedGamePanel = new GamePanel();

            new Thread(() -> {
                try {
                    ServerClient serverClient = new ServerClient(22222, connectionLatch);
                    serverClient.waitForClients(sharedGamePanel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            launch(args);
        }
    }


    public static CountDownLatch getConnectionLatch() {
        return connectionLatch;
    }

    public static GamePanel getSharedGamePanel() {
        return sharedGamePanel;
    }

}
