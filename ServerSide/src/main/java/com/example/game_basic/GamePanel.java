package com.example.game_basic;

import Entity.*;

import Network.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import tile.TileManager;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GamePanel {
    // Tile and screen size definitions
    private final int originalTileSize = 18;
    private final int scale = 3;
    public final int tileSize = originalTileSize * scale;  // 54
    public int checkFootballPositionBeforeX,checkFootballPositionBeforeY,checkFootballPositionAfterX,checkFootballPositionAfterY;
    int updateControlled = 0;
    private boolean gameKickedOff = false; // Tracks if the game has started with the first kick

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
//    public final int screenWidth = tileSize * maxScreenCol;  // 960
//    public final int screenHeight = tileSize * maxScreenRow; // 576

//    // Set full-screen size after the scene has been initialized
//    Screen screen = Screen.getPrimary();
//    int width = (int)screen.getVisualBounds().getWidth();
//    int height = (int)screen.getVisualBounds().getHeight();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int)screenSize.getWidth();
    int height = (int)screenSize.getHeight();

    public final int screenWidth = width;
    public final int screenHeight = height;
    public boolean teamControlCheck= false;
    public boolean GoalCheckForBoth = false;

    // World settings
    public final int maxWorldCol = 43;
    public final int maxworldRow = 34;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxworldRow;

    private final Canvas canvas;
    private final GraphicsContext gc;
    KeyHandler keyHandler = new KeyHandler();
//    public Player players = new Player(this, keyHandler,0,0,11);

    private final Lock stateLock = new ReentrantLock();
    private volatile boolean newGameStateApplied = false;

    List<Player> myPlayers = new ArrayList<>();
    List<OpPlayer> opPlayers = new ArrayList<>();
    public int checkTag = 0;
    public Player controlledMyPlayer;
    public OpPlayer controlledOpPlayer;
    public Football football = new Football(this, tileSize * 21, tileSize * 16);

    TileManager tileM = new TileManager(this);
    public CollisionCheck collisionChecker = new CollisionCheck(this);

    // Game loop variables
    private AnimationTimer gameLoop;

    public int goalCount = 0;
    public int OpGoalCount = 0;

    private int countdownTime = 300; // 5 minutes in seconds
    private long lastUpdateTime = System.currentTimeMillis(); // Track time updates


    public String OpPlayerName;

    public boolean checkWhichSideGoal = Boolean.parseBoolean(null);


    private void initializePlayers() {
//        for (int i = 0; i < 11; i++) {
//            int startX = (i % 5 + 1) * tileSize * 2;
//            int startY = (i / 5 + 1) * tileSize * 3;
//            players.add(new Player(this, keyHandler, startX, startY,i));
//        }

        /// My Players
        myPlayers.add(new Player(this, keyHandler, tileSize * 8, tileSize * 16,0,"right"));
        myPlayers.get(0).setBounds(tileSize * 8, tileSize * 8, tileSize * 13, tileSize * 19, 50);

        myPlayers.add(new Player(this, keyHandler, tileSize * 14, tileSize * 8,1,"right"));
        myPlayers.get(1).setBounds(tileSize * 10, tileSize * 17, tileSize * 8, tileSize * 8, 500);

        myPlayers.add(new Player(this, keyHandler, tileSize * 13, tileSize * 13,2,"right"));
        myPlayers.get(2).setBounds(tileSize * 10, tileSize * 17, tileSize * 13, tileSize * 13, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 13, tileSize * 19,3,"right"));
        myPlayers.get(3).setBounds(tileSize * 8, tileSize * 17, tileSize * 19, tileSize * 19, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 14, tileSize * 24,4,"right"));
        myPlayers.get(4).setBounds(tileSize * 8, tileSize * 17, tileSize * 24, tileSize * 24, 500);


        myPlayers.add(new Player(this, keyHandler, tileSize * 15, tileSize * 16,5,"right"));
        myPlayers.get(5).setBounds(tileSize * 18, tileSize * 24, tileSize * 19, tileSize * 19, 300);

        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 11,6,"right"));
        myPlayers.get(6).setBounds(tileSize * 18, tileSize * 24, tileSize * 11, tileSize * 11, 500);

        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 21,7,"right"));
        myPlayers.get(7).setBounds(tileSize * 18, tileSize * 24, tileSize * 21, tileSize * 13, 200);


        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 16,8,"right"));
        myPlayers.get(8).setBounds(tileSize * 21, tileSize * 34, tileSize * 16, tileSize * 16, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 20+20, tileSize * 15,9,"down"));
//        myPlayers.add(new Player(this, keyHandler, tileSize * 20, tileSize * 13,9,"down"));
        myPlayers.get(9).setBounds(tileSize * 21, tileSize * 34, tileSize * 8, tileSize * 8, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 20, tileSize * 20,10,"up"));
        myPlayers.get(10).setBounds(tileSize * 21, tileSize * 34, tileSize * 24, tileSize * 24, 500);

//        myPlayers.add(new Player(this, keyHandler, tileSize * 8, tileSize * 16, 0, "right"));
//        myPlayers.get(0).setBounds(tileSize * 13, tileSize * 13, tileSize * 13, tileSize * 19);

//        for (int i = 1; i <= 4; i++) {
//            myPlayers.add(new Player(this, keyHandler, tileSize * (13 + i), tileSize * 16, i, "right"));
//            myPlayers.get(i).setBounds(tileSize * 13, tileSize * 17, tileSize * 16, tileSize * 16);
//        }
//


//        for (int i = 5; i <= 10; i++) {
//            myPlayers.add(new Player(this, keyHandler, tileSize * (18 + i - 5), tileSize * 16, i, "right"));
//            myPlayers.get(i).setBounds(tileSize * 18, tileSize * 20, tileSize * 16, tileSize * 16);
//        }
        /// Opponent Players
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 34, tileSize * 16,0,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 8,1,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 13,2,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 19,3,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 24,4,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 27, tileSize * 16,5,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 11,6,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 21,7,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 16,8,"left"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 22, tileSize * 13,9,"down"));
        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 22, tileSize * 20,10,"up"));
    }

    public GamePanel() {
        // Create the canvas and set up the graphics context
        this.canvas = new Canvas(screenWidth, screenHeight);
        this.gc = canvas.getGraphicsContext2D();

        // Set up keyboard event listeners
        this.canvas.setFocusTraversable(true);
        this.canvas.setOnKeyPressed(keyHandler::handleKeyPressed);
        this.canvas.setOnKeyReleased(keyHandler::handleKeyReleased);

        initializePlayers();
        int x =  getClosestPlayerToFootball();
        controlledMyPlayer = myPlayers.get(x);
//        controlledMyPlayer = players;
        clearScreen(); // Set initial background to black
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    private boolean running = true; // Control the game loop

//    public void startGameLoop() {
//        gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                update();
//                draw();
////                generateGameState();
//            }
//        };
//        gameLoop.start();
//    }

public void startGameLoop() {
    gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            updateCountdownTimer(); // Update the timer
            update();               // Game logic
            draw();                 // Rendering logic
        }
    };
    gameLoop.start();
}

    private void updateCountdownTimer() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= 1000) { // Update every second
            if (countdownTime > 0) {
                countdownTime--; // Decrease the timer
            }
            lastUpdateTime = currentTime;
        }
    }




    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop(); // Stop the AnimationTimer
            System.out.println("Game loop stopped.");
        }
    }

//    public GameState generateGameState() {
//        GameState gameState = new GameState();
//
//        // Add my players
//        gameState.myPlayers = new ArrayList<>();
//        for (Player player : myPlayers) {
//            GameState.PlayerData data = new GameState.PlayerData();
//            data.id = player.getId();
//            data.x = player.worldX;
//            data.y = player.worldY;
////            System.out.println(data.id+" "+data.x+" "+data.y);
//            gameState.myPlayers.add(data);
//        }
//
//        // Add opponent players
////        gameState.opPlayers = new ArrayList<>();
////        for (OpPlayer player : opPlayers) {
////            GameState.PlayerData data = new GameState.PlayerData();
////            data.id = player.getId();
////            data.x = player.worldX;
////            data.y = player.worldY;
////            gameState.opPlayers.add(data);
////        }
//
//        // Add football position
//        gameState.footballX = football.worldX;
//        gameState.footballY = football.worldY;
//
//        System.out.println("In GamPanel: "+gameState.footballX+" "+gameState.footballY);
//
//
//
//        return gameState;
//    }

private void interpolateFootballPosition(double targetX, double targetY) {
    // Gradually move the football toward the target position
    football.worldX += (targetX - football.worldX) * 0.2;
    football.worldY += (targetY - football.worldY) * 0.2;
}

public synchronized GameState generateGameState() {
    GameState gameState = new GameState();

    // Add my players
    gameState.myPlayers = new ArrayList<>();
    for (Player player : myPlayers) {
        GameState.PlayerData data = new GameState.PlayerData();
        data.id = player.getId();
        data.x = player.worldX;
        data.y = player.worldY;
        data.direction = player.direction;
        gameState.myPlayers.add(data);
    }

    double serverPlayer = getClosestPlayerDistance();
    double clientPlayer = getClosestOpPlayerDistance();

//    System.out.println(serverPlayer+" "+clientPlayer);


    gameState.footballX = this.football.worldX;
    gameState.footballY = this.football.worldY;
    gameState.kickFootball = this.football.isKicked;
    gameState.myTeamControl = this.football.myTeamControl;

    gameState.velocityX = this.football.velocityX;
    gameState.velocityY = this.football.velocityY;

    gameState.SplayerName = String.valueOf(controlledMyPlayer);
//    gameState.ServerGoal = goalCount;
//                System.out.println("Yes, server Player is closet");
//    }
//    else {
//
//
//    }

    // Add football position


//    System.out.println("Server to client: " + gameState.kickFootball + " " + gameState.myTeamControl);

    return gameState;
}

//public synchronized GameState generateGameState() {
//    GameState gameState = new GameState();
//
//    // Add players
//    gameState.myPlayers = new ArrayList<>();
//    for (Player player : myPlayers) {
//        GameState.PlayerData data = new GameState.PlayerData();
//        data.id = player.getId();
//        data.x = player.worldX;
//        data.y = player.worldY;
//        data.direction = player.direction;
//        gameState.myPlayers.add(data);
//    }
//
//    // Update football state
//    gameState.footballX = football.worldX;
//    gameState.footballY = football.worldY;
//    gameState.kickFootball = football.isKicked;
//    gameState.myTeamControl = football.myTeamControl;
//    gameState.velocityX = football.velocityX;
//    gameState.velocityY = football.velocityY;
//
//    return gameState;
//}
//
//    public void applyGameState(GameState gameState) {
//        try {
//            if (!football.myTeamControl || gameState.myTeamControl) {
//                football.setDefaultValues(gameState.footballX, gameState.footballY);
//                football.velocityX = gameState.velocityX;
//                football.velocityY = gameState.velocityY;
//            }
//        } finally {
//            // Ensure consistency
//        }
//    }





    public void applyGameState(GameState gameState) {

//        interpolateFootballPosition(gameState.footballX, gameState.footballY);

        // Update players' positions
//        for (int i = 0; i < gameState.myPlayers.size(); i++) {
//            Player player = myPlayers.get(i);
//            player.worldX = gameState.myPlayers.get(i).x;
//            player.worldY = gameState.myPlayers.get(i).y;
//        }
//        OpGoalCount = gameState.ClientGoal;
        OpPlayerName = gameState.OplayerName;
        int i =0;
        for (OpPlayer player : opPlayers) {
            player.set_default_values(gameState.opPlayers.get(i).x,gameState.opPlayers.get(i).y,gameState.opPlayers.get(i).direction);
//                player.worldX = myPlayers.get(i).x;
//                player.worldY = myPlayers.get(i).y;
//            System.out.println(gameState.opPlayers.get(i).x);
            i++;
        }
//
//        // Update opponent players' positions
//        for (int i = 0; i < gameState.opPlayers.size(); i++) {
//            OpPlayer player = opPlayers.get(i);
//            player.worldX = gameState.opPlayers.get(i).x;
//            player.worldY = gameState.opPlayers.get(i).y;
//        }
        try {
            // Update the football position from server data
//            System.out.println("server GamePanel: " + gameState.footballX + " " + gameState.footballY);
            // Only update football position if it is not currently kicked
            // Update the football position from server data
//            System.out.println("server GamePanel: " + gameState.footballX + " " + gameState.footballY);
            // Only update football position if it is not currently kicked
            double serverPlayer = getClosestPlayerDistance();
            double clientPlayer = getClosestOpPlayerDistance();
//            if ((Math.abs(controlledMyPlayer.worldX - football.worldX) > 0 &&
//                    Math.abs(controlledMyPlayer.worldY - football.worldY) > 0) || (gameState.myTeamControl)) {
//                football.setDefaultValues(gameState.footballX, gameState.footballY);
//            }
            if (gameState.footballX == 0 && gameState.footballY == 0 && !football.isKicked) {
                System.err.println("Unexpected reset detected in applyGameState!");
                gameState.footballX = football.worldX;
                gameState.footballY = football.worldY;
            }

            if(football.myTeamControl){
                gameState.footballX = football.worldX;
                gameState.footballY = football.worldY;
//                System.out.println("for f1: "+football.worldX+" "+football.worldY);
//                football.setDefaultValues(football.worldX, football.worldY);
            }

            if(!football.myTeamControl && !football.checkMyTeamControl && !GoalCheckForBoth){
//                System.out.println("for f2: "+gameState.footballX+" "+gameState.footballY);
                football.setDefaultValues(gameState.footballX, gameState.footballY);
            }

            boolean goalDetected = collisionChecker.goalCheck(football,this);
            System.out.println("Goal: "+goalDetected);

            if (goalDetected && !GoalCheckForBoth ) {



                System.out.println("GOAL!");
//                incrementGoalCount(); // Increment goal count in GamePanel
                resetPositionsAfterGoal(); // Reset positions
                GoalCheckForBoth = true;
                gameKickedOff = false;
//                return;
//            return 0;
            }

        } finally {
//            stateLock.unlock(); // Release the lock
        }

        // Update football position
//        football.worldX = gameState.footballX;
//        football.worldY = gameState.footballY;
    }

    private double getClosestPlayerDistance() {
        Player closest = null;
        double closestDistance = Double.MAX_VALUE;


        for (Player player : myPlayers) {
            double distance = Math.hypot(player.worldX - football.worldX, player.worldY - football.worldY);
//            System.out.println(player+" "+distance+" "+player.worldX+" "+player.worldY);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = player;
            }
        }
//        controlledPlayer = closest;
        return closestDistance;
    }

    private double getClosestOpPlayerDistance() {
        OpPlayer closest = null;
        double closestDistance = Double.MAX_VALUE;


        for (OpPlayer player : opPlayers) {
            double distance = Math.hypot(player.worldX - football.worldX, player.worldY - football.worldY);
//            System.out.println(player+" "+distance+" "+player.worldX+" "+player.worldY);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = player;
            }
        }
//        controlledPlayer = closest;
        return closestDistance;
    }

    private int getClosestPlayerToFootball() {
        Player closest = null;
        int playerID = 0;
        double closestDistance = Double.MAX_VALUE;


        for (Player player : myPlayers) {
            double distanceMyTeam = Math.hypot(player.worldX - football.worldX, player.worldY - football.worldY);
//            System.out.println(player+" "+distance+" "+player.worldX+" "+player.worldY);
            if (distanceMyTeam < closestDistance) {
                closestDistance = distanceMyTeam;
                closest = player;
                playerID = player.ID;
            }
        }

//        controlledMyPlayer = closest;
        return playerID;
    }

    private int getClosestOpPlayerToFootball() {
        OpPlayer closest = null;
        int playerID = 0;
        double closestDistance = Double.MAX_VALUE;


        for (OpPlayer player : opPlayers) {
            double distance = Math.hypot(player.worldX - football.worldX, player.worldY - football.worldY);
//            System.out.println(player+" "+distance+" "+player.worldX+" "+player.worldY);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = player;
                playerID = player.ID;
            }
        }
//        controlledMyPlayer = closest;
//        return closest;
        return playerID;
    }

    public void incrementGoalCount() {
        goalCount++;
        System.out.println("Goal Count: " + goalCount);
    }

    public void resetPositionsAfterGoal() {
        new Thread(() -> {
            try {
                // Pause for 2 seconds
                Thread.sleep(2000);
//                football.setDefaultValues(tileSize * 10,tileSize * 10);
                GoalCheckForBoth = false;

                // Reset football position on the JavaFX Application Thread
                Platform.runLater(() -> {
//                    football.setDefaultValues(tileSize * 10,tileSize * 10);
                    football.resetPosition(); // Reset position
//                    incrementGoalCount();

                    // Ensure football is moved off any goal tiles
                    int footballTileX = football.worldX / tileSize;
                    int footballTileY = football.worldY / tileSize;

                    if (tileM.tile[tileM.mapTile[footballTileX][footballTileY]].football_goal_collision) {
//                        football.setDefaultValues(tileSize * 10, tileSize * 10); // Example safe position
                    }

                    if(checkWhichSideGoal == true){
                        OpGoalCount++;

                        int tt = 0;
                    for (Player player : myPlayers) {
                        if(tt==9){
                            player.resetForOnlyOnePlayer(tileSize * 20, tileSize * 13);
                        }else {
                            player.resetPosition();

                        }
                         // Implement reset logic in Player
                        tt++;
                    }
                    }
                    else if(checkWhichSideGoal == false){
                        incrementGoalCount();
                        // Reset player positions
                        for (Player player : myPlayers) {
                            player.resetPosition(); // Reset logic for Player
                        }

                    }


                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


//    public void resetPositionsAfterGoal() {
//        new Thread(() -> {
//            try {
//                // Pause for 5 seconds
//                Thread.sleep(2000);
//                GoalCheckForBoth = false;
////                System.out.println("check Goal!!!!!!!!!!!!!!!!");
//                football.resetPosition();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            // Reset all player and football positions
//            Platform.runLater(() -> {
//                int footballTileX = football.worldX / tileSize;
//                int footballTileY = football.worldY / tileSize;
//
//                int changeForWhichTeam = tileM.mapTile[footballTileX][footballTileY];
//
//                System.out.println(tileM.mapTile[footballTileX][footballTileY]);
//
//                if(changeForWhichTeam== 649 || changeForWhichTeam==650 || changeForWhichTeam==692 || changeForWhichTeam==693 || changeForWhichTeam==735 || changeForWhichTeam==736 ){
//                    int tt = 0;
//                    for (Player player : myPlayers) {
//                        if(tt==9){
//                            player.resetForOnlyOnePlayer(tileSize * 20, tileSize * 13);
//                        }else {
//                            player.resetPosition();
//
//                        }
//                         // Implement reset logic in Player
//                        tt++;
//                    }
//                }else {
//                    for (Player player : myPlayers) {
//                        player.resetPosition(); // Implement reset logic in Player
//                    }
//
//                }
//
//
//                football.resetPosition(); // Implement reset logic in Football
//            });
//        }).start();
//    }


//    public void update() {
//
////        System.out.println("my Update");
//
////        System.out.println("In GamPanel: " + this.football.worldX + " " + this.football.worldY);
//
////        for (Player player : players) {
////            player.update();
////        }
////        System.out.println("In GamPanel: " + football.worldX + " " + football.worldY);
//        checkFootballPositionBeforeX = football.worldX;
//        checkFootballPositionBeforeY = football.worldY;
////        if(updateControlled == 1 && Math.abs(checkFootballPositionBeforeX - checkFootballPositionAfterX)==0 && Math.abs(checkFootballPositionBeforeY - checkFootballPositionAfterY)==0){
////            controlledMyPlayer = getClosestPlayerToFootball();
////            System.out.println("in: "+updateControlled);
////            updateControlled = 0;
////        }
//
//        int x = getClosestPlayerToFootball();
//        int y = getClosestOpPlayerToFootball();
//
//        // Start auto-move for players only after the game has been kicked off
//        if (gameKickedOff) {
//            for (Player player : myPlayers) {
//                if (player != controlledMyPlayer) {
//                    player.autoMove();
//                }
//            }
//        }
////        System.out.println(controlledMyPlayer);
//
//        controlledMyPlayer.update(football);
//        int checkFootballPosition = football.update(controlledMyPlayer);
//
////        System.out.println(checkFootballPosition);
//
//        if (checkFootballPosition == 0){
//
//            controlledMyPlayer = myPlayers.get(x);
//            controlledOpPlayer = opPlayers.get(y);
//            if(Math.abs(controlledMyPlayer.worldX - football.worldX) < tileSize && Math.abs(controlledMyPlayer.worldY - football.worldY) < tileSize){
//                if(x < y){
//                    football.update(controlledMyPlayer);
//
//                }
//
//            }
//
//        }
//
////        System.out.println(football.x+" "+football.y);
////        while (football.isKicked){
////            System.out.println(football.lastFootballX()+" "+football.lastFootballY());
////
////        }
////        System.out.println(getClosestPlayerToFootball());
////        checkFootballPositionAfterX = football.worldX;
////        checkFootballPositionAfterY = football.worldY;
//
//
//        // Kick the football when the spacebar is pressed and the player is close
//        if (keyHandler.kickPressed) {
////            System.out.println(player);
////            football.kick(player);
////            System.out.println(football.lastFootballX()+" "+football.lastFootballY());
////            System.out.println(getClosestPlayerToFootball());
//            controlledMyPlayer = myPlayers.get(x);
//            football.kick(controlledMyPlayer,keyHandler.UpArrowPressed);
//            keyHandler.kickPressed = false; // Prevent multiple kicks
////            keyHandler.UpArrowPressed = false; // Prevent multiple kicks
////            System.out.println("before: "+updateControlled);
//            controlledMyPlayer.setControlled(false); // Stop controlling this player
//            updateControlled = 1;
//
//            gameKickedOff = true; // Start auto-movement after the first kick
////            System.out.println("after: "+updateControlled);
//
//        }
////        if (updateControlled == 1){
////            System.out.println(football.x+" "+football.y);
////
////            controlledMyPlayer = getClosestPlayerToFootball();
////            System.out.println("hello"+getClosestPlayerToFootball());
////
////        }
//
////        if(keyH.upPressed || keyH.rightPressed || keyH.leftPressed || keyH.downPressed){
////            controlledMyPlayer = getClosestPlayerToFootball();
////
////        }
//        // Calculate the closest player to the football
////        if (!football.isKicked) {
////            controlledMyPlayer = getClosestPlayerToFootball();
//
////            System.out.println("Closest Player: " + closestPlayer);
////        }
//    }

public synchronized void update() {
    stateLock.lock(); // Acquire the lock
    try {
        if (newGameStateApplied) {
            // Skip normal updates if a new game state has been applied
            newGameStateApplied = false;
            return;
        }

        checkFootballPositionBeforeX = football.worldX;
        checkFootballPositionBeforeY = football.worldY;

        // Update the football's position (reflecting server updates)
//        football.update(controlledPlayer);
        // Only update the football if it's not kicked
        if (!football.isKicked) {
            football.update(controlledMyPlayer);
        }

        // Start auto-move for players only after the game has been kicked off
        if (gameKickedOff) {
            for (Player player : myPlayers) {
                if (player != controlledMyPlayer) {
                    player.autoMove();
                }
            }
        }

        int x = getClosestPlayerToFootball();
        int y = getClosestOpPlayerToFootball();

        controlledMyPlayer.update(football);
        int checkFootballPosition = football.update(controlledMyPlayer);

        if (checkFootballPosition == 0) {
            controlledMyPlayer = myPlayers.get(x);
            if (Math.abs(controlledMyPlayer.worldX - football.worldX) < tileSize &&
                    Math.abs(controlledMyPlayer.worldY - football.worldY) < tileSize) {
                football.update(controlledMyPlayer);
            }
        }
        checkFootballPositionAfterX = football.worldX;
        checkFootballPositionAfterY = football.worldY;

        // Kick the football when the spacebar is pressed and the player is close
        if (keyHandler.kickPressed) {
            controlledMyPlayer = myPlayers.get(x);
            football.kick(controlledMyPlayer, keyHandler.UpArrowPressed);
            keyHandler.kickPressed = false; // Prevent multiple kicks

            controlledMyPlayer.setControlled(false); // Stop controlling this player
            updateControlled = 1;

            gameKickedOff = true; // Start auto-movement after the first kick
        }
    } finally {
        stateLock.unlock(); // Release the lock
    }
}

    public void draw() {
//        System.out.println("Drawing server: " + football.worldX + ", " + football.worldY);
    int checkTeam = 0;
        // Clear the canvas
        clearScreen();
        tileM.draw(gc);
        for (Player player : myPlayers) {
            if(controlledMyPlayer == player){
                checkTeam=0;
            }
            player.draw(gc);
        }
        for (OpPlayer player : opPlayers) {
            player.draw(gc);
        }
//        players.draw(gc);

        if(checkTeam==0){
            football.draw(gc,checkTeam);

        }
        else{
            football.draw(gc, -1);
        }

        drawScoreboard();


    }



    private void drawScoreboard() {
        // Draw the background for the scoreboard
        gc.setFill(Color.DARKGRAY); // Set the background color
        gc.fillRect(10, 10, 400, 50); // Draw the rectangle (adjust the position and size as needed)

        // Convert remaining time to minutes and seconds
        int minutes = countdownTime / 60;
        int seconds = countdownTime % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);

        // Draw the text on top of the background
        gc.setFill(Color.WHITE); // Set text color
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Set font style and size
        gc.fillText("Time: " + timeFormatted + " | My Team: " + goalCount + " - Opponent: " + OpGoalCount + " | OpPlayerName: " + OpPlayerName,
                20, 35); // Adjust coordinates for proper alignment
    }



    private void clearScreen() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
