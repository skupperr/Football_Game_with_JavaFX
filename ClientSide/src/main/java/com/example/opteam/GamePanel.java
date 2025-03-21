package com.example.opteam;

import Entity.*;

import Network.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import tile.TileManager;


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
    public boolean teamControlCheck = false;

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;

    public int goalCount = 0;
    public int OPgoalCount = 0;

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

    // World settings
    public final int maxWorldCol = 43;
    public final int maxworldRow = 34;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxworldRow;

    private final Lock stateLock = new ReentrantLock();
    private volatile boolean gameStateUpdated = false;
    private volatile boolean newGameStateApplied = false;
    private final Canvas canvas;
    private final GraphicsContext gc;
    KeyHandler keyHandler = new KeyHandler();
//    public Player players = new Player(this, keyHandler,0,0,11);

    List<Player> myPlayers = new ArrayList<>();
    List<OpPlayer> opPlayers = new ArrayList<>();
    public OpPlayer controlledPlayer;
    public Football football = new Football(this, tileSize * 21, tileSize * 16);

    TileManager tileM = new TileManager(this);
    public CollisionCheck collisionChecker = new CollisionCheck(this);

    public boolean OpteamControlCheck= false;
    public boolean OpCheck= false;

    private int countdownTime = 300; // 5 minutes in seconds
    private long lastUpdateTime = System.currentTimeMillis(); // Track time updates


    public boolean GoalCheckForBoth = false;


    public boolean checkWhichSideGoal = Boolean.parseBoolean(null);


    public double veloX=-1;
    public double veloY=-1;

    public String OpPlayerName ;

    // Game loop variables
    private AnimationTimer gameLoop;


    private void initializePlayers() {
//        for (int i = 0; i < 11; i++) {
//            int startX = (i % 5 + 1) * tileSize * 2;
//            int startY = (i / 5 + 1) * tileSize * 3;
//            players.add(new Player(this, keyHandler, startX, startY,i));
//        }

        /// My Players
        myPlayers.add(new Player(this, keyHandler, tileSize * 8, tileSize * 16,0,"right"));
//        myPlayers.get(0).setBounds(tileSize * 8, tileSize * 8, tileSize * 13, tileSize * 19, 0);

        myPlayers.add(new Player(this, keyHandler, tileSize * 14, tileSize * 8,1,"right"));
//        myPlayers.get(1).setBounds(tileSize * 10, tileSize * 17, tileSize * 8, tileSize * 8, 500);

        myPlayers.add(new Player(this, keyHandler, tileSize * 13, tileSize * 13,2,"right"));
//        myPlayers.get(2).setBounds(tileSize * 10, tileSize * 17, tileSize * 13, tileSize * 13, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 13, tileSize * 19,3,"right"));
//        myPlayers.get(3).setBounds(tileSize * 8, tileSize * 17, tileSize * 19, tileSize * 19, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 14, tileSize * 24,4,"right"));
//        myPlayers.get(4).setBounds(tileSize * 8, tileSize * 17, tileSize * 24, tileSize * 24, 500);

        myPlayers.add(new Player(this, keyHandler, tileSize * 15, tileSize * 16,5,"right"));
//        myPlayers.get(5).setBounds(tileSize * 21, tileSize * 34, tileSize * 19, tileSize * 19, 300);

        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 11,6,"right"));
//        myPlayers.get(6).setBounds(tileSize * 21, tileSize * 34, tileSize * 11, tileSize * 11, 500);

        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 21,7,"right"));
//        myPlayers.get(7).setBounds(tileSize * 21, tileSize * 34, tileSize * 13, tileSize * 13, 200);

        myPlayers.add(new Player(this, keyHandler, tileSize * 17, tileSize * 16,8,"right"));
//        myPlayers.get(8).setBounds(tileSize * 21, tileSize * 34, tileSize * 16, tileSize * 16, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 20+20, tileSize * 15,9,"down"));
//        myPlayers.get(9).setBounds(tileSize * 21, tileSize * 34, tileSize * 8, tileSize * 8, 700);

        myPlayers.add(new Player(this, keyHandler, tileSize * 20, tileSize * 20,10,"up"));
//        myPlayers.get(10).setBounds(tileSize * 21, tileSize * 34, tileSize * 24, tileSize * 24, 500);

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
        opPlayers.get(0).setBounds(tileSize * 34, tileSize * 34, tileSize * 13, tileSize * 19, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 28, tileSize * 8,1,"left"));
        opPlayers.get(1).setBounds(tileSize * 25, tileSize * 32, tileSize * 8, tileSize * 9, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 13,2,"left"));
        opPlayers.get(2).setBounds(tileSize * 25, tileSize * 32, tileSize * 13, tileSize * 13, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 29, tileSize * 19,3,"left"));
        opPlayers.get(3).setBounds(tileSize * 25, tileSize * 32, tileSize * 19, tileSize * 19, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 28, tileSize * 24,4,"left"));
        opPlayers.get(4).setBounds(tileSize * 25, tileSize * 27, tileSize * 24, tileSize * 24, 0);


        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 27, tileSize * 16,5,"left"));
        opPlayers.get(5).setBounds(tileSize * 15, tileSize * 32, tileSize * 16, tileSize * 16, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 11,6,"left"));
        opPlayers.get(6).setBounds(tileSize * 15, tileSize * 32, tileSize * 11, tileSize * 11, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 16,8,"left"));
        opPlayers.get(7).setBounds(tileSize * 8, tileSize * 17, tileSize * 16, tileSize * 16, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 25, tileSize * 21,7,"left"));
        opPlayers.get(8).setBounds(tileSize * 15, tileSize * 32, tileSize * 21, tileSize * 21, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 22, tileSize * 13,9,"down"));
//        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 22-25, tileSize * 15,9,"down"));
        opPlayers.get(9).setBounds(tileSize * 8, tileSize * 17, tileSize * 8, tileSize * 8, 0);

        opPlayers.add(new OpPlayer(this, keyHandler, tileSize * 22, tileSize * 20,10,"up"));
        opPlayers.get(10).setBounds(tileSize * 8, tileSize * 17, tileSize * 24, tileSize * 24, 0);

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
        controlledPlayer = getClosestOpPlayerToFootball();

//        controlledPlayer = players;
        clearScreen(); // Set initial background to black
//        update();
//        draw();

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

//    public void startGameLoop() {
//        gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
////                if (gameStateUpdated) {
//                    update();
////                    gameStateUpdated = false;
////                }
//                draw();
//            }
//
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

    private boolean running = true; // Control the game loop


    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop(); // Stop the AnimationTimer
            System.out.println("Game loop stopped.");
        }
    }

    private void reconcileFootball(GameState gameState) {
        // Calculate the difference between the predicted and actual positions
        double deltaX = gameState.footballX - football.worldX;
        double deltaY = gameState.footballY - football.worldY;

        // If the difference is significant, adjust the position
        if (Math.abs(deltaX) > 5 || Math.abs(deltaY) > 5) {
            football.worldX += deltaX * 0.1; // Smooth interpolation
            football.worldY += deltaY * 0.1;
        } else {
            football.worldX = gameState.footballX;
            football.worldY = gameState.footballY;
        }
    }



    public synchronized GameState generateGameState() {
        GameState gameState = new GameState();

        // Add my players
        gameState.opPlayers = new ArrayList<>();
        for (OpPlayer player : opPlayers) {
            GameState.PlayerData data = new GameState.PlayerData();
            data.id = player.getId();
            data.x = player.worldX;
            data.y = player.worldY;
            gameState.opPlayers.add(data);
        }
        football.update(controlledPlayer);

        // Add football position
//        if(!OpteamControlCheck){
        if(!OpCheck){
            gameState.footballX = this.football.worldX;
            gameState.footballY = this.football.worldY;

        }

//            gameState.kickFootball = this.football.isKicked;
            gameState.myTeamControl = this.football.myTeamControl;
//        if (Math.abs(controlledPlayer.worldX - football.worldX) <= tileSize && Math.abs(controlledPlayer.worldY - football.worldY) <= tileSize) {
            gameState.kickFootball = football.checkMyTeamControl;


            gameState.OplayerName = String.valueOf(controlledPlayer);
//            gameState.ClientGoal = goalCount;

//            System.out.println(gameState.kickFootball);
//        }
//        else{
//            gameState.kickFootball = football.checkMyTeamControl;
//        }

//            System.out.println("isKicked: "+this.football.myTeamControl+ " "+this.football.isKicked);

//        }


//        System.out.println("In client GamPanel: " + gameState.footballX + " " + gameState.footballY);

        return gameState;
    }

    double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    public void applyGameState(GameState gameState) {
//        reconcileFootball(gameState);



//        stateLock.lock(); // Acquire the lock
        try {
            OpCheck = gameState.myTeamControl;
            veloX = gameState.velocityX;
            veloY = gameState.velocityY;

            OpPlayerName = gameState.SplayerName;

//            OPgoalCount = gameState.ServerGoal;

            int i =0;
            for (Player player : myPlayers) {
                player.set_default_values(gameState.myPlayers.get(i).x,gameState.myPlayers.get(i).y,gameState.myPlayers.get(i).direction);

//                player.worldX = myPlayers.get(i).x;
//                player.worldY = myPlayers.get(i).y;
//                System.out.println(gameState.myPlayers.get(i).x);
                i++;
            }
            // Update the football position from server data
//            System.out.println("Client GamePanel: " + gameState.footballX + " " + gameState.footballY);
            // Only update football position if it is not currently kicked
            double serverPlayer = getClosestPlayerDistance();
            double clientPlayer = getClosestOpPlayerDistance();

//            System.out.println(gameState.kickFootball+" "+gameState.myTeamControl);

//            if ((Math.abs(controlledPlayer.worldX - football.worldX) >0 &&
//                    Math.abs(controlledPlayer.worldY - football.worldY) >0) || (gameState.myTeamControl)) {
//                football.setDefaultValues(gameState.footballX, gameState.footballY);
//            }
            double lerpFactor = 0.1;
            OpteamControlCheck= gameState.myTeamControl;
            if (!gameState.myTeamControl && !GoalCheckForBoth) {
                // Only update the position if the ball is not controlled by the team

//                gameState.footballX = (int) lerp(football.worldX, gameState.footballX, lerpFactor);
//                gameState.footballY = (int) lerp(football.worldY, gameState.footballY, lerpFactor);
//                football.worldX = gameState.footballX;
//                football.worldY = gameState.footballY;
                football.setDefaultValues(gameState.footballX, gameState.footballY);
//                football.setDefaultValues(football.worldX, football.worldY);
            }

// Update velocity and control state
//            football.velocityX = gameState.velocityX;
//            football.velocityY = gameState.velocityY;
//            football.isKicked = gameState.kickFootball;


            gameStateUpdated = true;

            boolean goalDetected = collisionChecker.goalCheck(football,this);
            if (goalDetected && !GoalCheckForBoth ) {

                System.out.println("GOAL!");
//                incrementGoalCount(); // Increment goal count in GamePanel
                resetPositionsAfterGoal(); // Reset positions
                GoalCheckForBoth = true;
                gameKickedOff = false;
//            return 0;
            }

        } finally {
//            stateLock.unlock(); // Release the lock
        }

            /// //???Edit
//            double serverPlayer = getClosestPlayerDistance();
//            double clientPlayer = getClosestOpPlayerDistance();
//
////            System.out.println(gameState.kickFootball+" "+gameState.myTeamControl);
//
////            if ((Math.abs(controlledPlayer.worldX - football.worldX) >0 &&
////                    Math.abs(controlledPlayer.worldY - football.worldY) >0) || (gameState.myTeamControl)) {
////                football.setDefaultValues(gameState.footballX, gameState.footballY);
////            }
//
////            OpteamControlCheck= gameState.myTeamControl;
////            if(!gameState.myTeamControl){
////                football.setDefaultValues(gameState.footballX, gameState.footballY);
////            }
////            OpteamControlCheck = gameState.myTeamControl;
//
//            // Predict football position if the client is controlling it
//            if (OpteamControlCheck) {
//                // Predict football position based on local input
//                football.worldX += football.velocityX; // Use football's velocity
//                football.worldY += football.velocityY;
//
//                gameState.footballX = football.worldX;
//                gameState.footballY = football.worldY;
//            } else {
//                // Smoothly interpolate football position for non-local control
//                double deltaX = gameState.footballX - football.worldX;
//                double deltaY = gameState.footballY - football.worldY;
//
//                // Interpolate to server position
//                football.worldX += deltaX * 0.1; // Adjust interpolation speed (0.2 = smooth)
//                football.worldY += deltaY * 0.1;
//            }
//
//            // Synchronize control flag
//            OpteamControlCheck = gameState.myTeamControl;
////            else if(){
////                football.setDefaultValues(football, gameState.footballY);
////            }
////            if(serverPlayer<=clientPlayer){
////                football.setDefaultValues(gameState.footballX, gameState.footballY);
////
////            }
////            else {
////                System.out.println("Yes, Client Player is closet");
//
////            }
//
////            if (!football.isKicked) {
//
////            }
//            gameStateUpdated = true;
//
//        } finally {
////            stateLock.unlock(); // Release the lock
//        }

        /// ///????edit

        // Update players' positions
//        System.out.println(gameState);
//        for (int i = 0; i < gameState.myPlayers.size(); i++) {
//            Player player = myPlayers.get(i);
//            player.worldX = gameState.myPlayers.get(i).x;
//            player.worldY = gameState.myPlayers.get(i).y;
//        }
//
//        // Update opponent players' positions
//        for (int i = 0; i < gameState.opPlayers.size(); i++) {
//            OpPlayer player = opPlayers.get(i);
//            player.worldX = gameState.opPlayers.get(i).x;
//            player.worldY = gameState.opPlayers.get(i).y;
//        }
//        System.out.println("Client GamePanel: "+gameState.footballX+" "+gameState.footballY);

        // Update football position
//        this.football.worldX = gameState.footballX;
//        this.football.worldY = gameState.footballY;
//
////        football.setDefaultValues(gameState.footballX, gameState.footballY);
////        System.out.println("apply: "+gameState.footballX+" "+gameState.footballY);
//
//
//        update();
//        draw();
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


    private OpPlayer getClosestOpPlayerToFootball() {
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
        return closest;
    }
    private Player getClosestPlayerToFootball() {
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
        return closest;
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
                    football.resetPosition(); // Reset position
//                    incrementGoalCount();

                    // Ensure football is moved off any goal tiles
                    int footballTileX = football.worldX / tileSize;
                    int footballTileY = football.worldY / tileSize;

                    if (tileM.tile[tileM.mapTile[footballTileX][footballTileY]].football_goal_collision) {
//                        football.setDefaultValues(tileSize * 10, tileSize * 10); // Example safe position
                    }
                    if(checkWhichSideGoal == true){

                        incrementGoalCount();

                        int tt = 0;
                        for (OpPlayer player : opPlayers) {
                            if(tt==9){
                                player.resetForOnlyOnePlayer(tileSize * 22, tileSize * 15);
                            }else {
                                player.resetPosition();

                            }
                            // Implement reset logic in Player
                            tt++;
                        }
                    }
                    else if(checkWhichSideGoal == false){
                        OPgoalCount++;
//                        incrementGoalCount();
                        // Reset player positions
                        for (OpPlayer player : opPlayers) {
                            player.resetPosition(); // Reset logic for Player
                        }

                    }

//                    // Reset player positions
//                    for (OpPlayer player : opPlayers) {
//                        player.resetPosition(); // Reset logic for Player
//                    }
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
////                for (OpPlayer player : opPlayers) {
////                    player.resetPosition(); // Implement reset logic in Player
////                }
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
////                System.out.println(tileM.mapTile[footballTileX][footballTileY]);
//
//                if(changeForWhichTeam!= 649 || changeForWhichTeam!=650 || changeForWhichTeam!=692 || changeForWhichTeam!=693 || changeForWhichTeam!=735 || changeForWhichTeam!=736 ){
//                    int tt = 0;
//                    for (OpPlayer player : opPlayers) {
//                        if(tt==9){
//                            player.resetForOnlyOnePlayer(tileSize * 22-25, tileSize * 15);
//                        }else {
//                            player.resetPosition();
//
//                        }
//                        // Implement reset logic in Player
//                        tt++;
//                    }
//                }else {
//                    for (OpPlayer player : opPlayers) {
//                        player.resetPosition(); // Implement reset logic in Player
//                    }
//
//                }
//
//                football.resetPosition(); // Implement reset logic in Football
//            });
//        }).start();
//    }

//    public void update() {
//        System.out.println("Update client: " + football.worldX + ", " + football.worldY);
//
////        for (Player player : players) {
////            player.update();
////        }
//        checkFootballPositionBeforeX = football.worldX;
//        checkFootballPositionBeforeY = football.worldY;
////        if(updateControlled == 1 && Math.abs(checkFootballPositionBeforeX - checkFootballPositionAfterX)==0 && Math.abs(checkFootballPositionBeforeY - checkFootballPositionAfterY)==0){
////            controlledPlayer = getClosestPlayerToFootball();
////            System.out.println("in: "+updateControlled);
////            updateControlled = 0;
////        }
//
//        // Start auto-move for players only after the game has been kicked off
//        if (gameKickedOff) {
//            for (OpPlayer player : opPlayers) {
//                if (player != controlledPlayer) {
//                    player.autoMove();
//                }
//            }
//        }
//
//        controlledPlayer.update(football);
//        int checkFootballPosition = football.update(controlledPlayer);
//
////        System.out.println(checkFootballPosition);
//
//        if (checkFootballPosition == 0){
//            controlledPlayer = getClosestPlayerToFootball();
//            if(Math.abs(controlledPlayer.worldX - football.worldX) < tileSize && Math.abs(controlledPlayer.worldY - football.worldY) < tileSize){
//                football.update(controlledPlayer);
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
//        checkFootballPositionAfterX = football.worldX;
//        checkFootballPositionAfterY = football.worldY;
//
//
//        // Kick the football when the spacebar is pressed and the player is close
//        if (keyHandler.kickPressed) {
////            System.out.println(player);
////            football.kick(player);
////            System.out.println(football.lastFootballX()+" "+football.lastFootballY());
////            System.out.println(getClosestPlayerToFootball());
//            controlledPlayer = getClosestPlayerToFootball();
//            football.kick(controlledPlayer,keyHandler.UpArrowPressed);
//            keyHandler.kickPressed = false; // Prevent multiple kicks
////            keyHandler.UpArrowPressed = false; // Prevent multiple kicks
////            System.out.println("before: "+updateControlled);
//            controlledPlayer.setControlled(false); // Stop controlling this player
//            updateControlled = 1;
//
//            gameKickedOff = true; // Start auto-movement after the first kick
////            System.out.println("after: "+updateControlled);
//
//        }
////        if (updateControlled == 1){
////            System.out.println(football.x+" "+football.y);
////
////            controlledPlayer = getClosestPlayerToFootball();
////            System.out.println("hello"+getClosestPlayerToFootball());
////
////        }
//
////        if(keyH.upPressed || keyH.rightPressed || keyH.leftPressed || keyH.downPressed){
////            controlledPlayer = getClosestPlayerToFootball();
////
////        }
//        // Calculate the closest player to the football
////        if (!football.isKicked) {
////            controlledPlayer = getClosestPlayerToFootball();
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
            football.update(controlledPlayer);
        }

        // Start auto-move for players only after the game has been kicked off
        if (gameKickedOff) {
            for (OpPlayer player : opPlayers) {
                if (player != controlledPlayer) {
                    player.autoMove();
                }
            }
        }

        controlledPlayer.update(football);
        int checkFootballPosition = football.update(controlledPlayer);

        if (checkFootballPosition == 0) {
            controlledPlayer = getClosestOpPlayerToFootball();
            if (Math.abs(controlledPlayer.worldX - football.worldX) < tileSize &&
                    Math.abs(controlledPlayer.worldY - football.worldY) < tileSize) {
                football.update(controlledPlayer);
            }
        }
        checkFootballPositionAfterX = football.worldX;
        checkFootballPositionAfterY = football.worldY;

        // Kick the football when the spacebar is pressed and the player is close
        if (keyHandler.kickPressed) {
            controlledPlayer = getClosestOpPlayerToFootball();
            football.kick(controlledPlayer, keyHandler.UpArrowPressed);
            keyHandler.kickPressed = false; // Prevent multiple kicks

            controlledPlayer.setControlled(false); // Stop controlling this player
            updateControlled = 1;

            gameKickedOff = true; // Start auto-movement after the first kick
        }
    } finally {
        stateLock.unlock(); // Release the lock
    }
}


    public void draw() {

//        System.out.println("Drawing client: " + football.worldX + ", " + football.worldY);
        // Clear the canvas
        clearScreen();
        tileM.draw(gc);
        for (Player player : myPlayers) {
            player.draw(gc);
        }
        for (OpPlayer player : opPlayers) {
            player.draw(gc);
        }
//        players.draw(gc);
        football.draw(gc);

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
        gc.fillText("Time: " + timeFormatted + " | My Team: " + goalCount + " - Opponent: " + OPgoalCount + " | OpPlayerName: " + OpPlayerName,
                20, 35); // Adjust coordinates for proper alignment
    }

    private void clearScreen() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
