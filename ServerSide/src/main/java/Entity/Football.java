package Entity;

//import com.example.democheck.main.*;
//import com.example.democheck.main.GamePanel;
import com.example.game_basic.GamePanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Football extends entity {
    private final GamePanel gp;
    public boolean isKicked = false;
    public double velocityX = 0;
    public double velocityY = 0;
    private Player controlledPlayer;
    int UpdateFootball = 0;
    public final int screenX;
    public final int screenY;
    public int returnStuct =0;
    public final Lock positionLock = new ReentrantLock();

    public boolean checkMyTeamControl = false;
    public long lastControlSwitchTime = 0; // Time of the last control switch


//    int updateAfterKickX=0;
//    int updateAfterKickY=0;

    public Football(GamePanel gp, int worldX, int worldY) {
        this.gp = gp;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        speed = 5;

        this.worldX = worldX;
        this.worldY = worldY;

        this.initialX = worldX;
        this.initialY = worldY;

        solidArea = new java.awt.Rectangle();
        solidArea.x = 0; // Adjust based on the football's sprite
        solidArea.y = 0; // Adjust based on the football's sprite
        solidArea.width = gp.tileSize / 2; // Set a reasonable width
        solidArea.height = gp.tileSize / 2; // Set a reasonable height

//        setDefaultValues();
        getFootballImage();
    }

    public void setDefaultValues(int worldX, int worldY) {
        positionLock.lock();
        try {
            if (worldX >= 0 && worldY >= 0) {
                this.worldX = worldX;
                this.worldY = worldY;}

//            myTeamControl = false;
//            checkMyTeamControl = false;
//            System.out.println("Updated Football Position: " + this.worldX + ", " + this.worldY);
        } finally {
            positionLock.unlock();
        }
    }

    public void resetPosition() {
        this.worldX = initialX;
        this.worldY = initialY;
        System.out.println("Reset: "+worldX+" "+worldY);
        this.velocityX = 0;
        this.velocityY = 0;
        this.isKicked = false;
        this.myTeamControl = false;
    }


    public void getFootballImage() {
        try {
            up1 = new Image(getClass().getResourceAsStream("/football/football5.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int update(Player player) {
        controlledPlayer = player;

        // Check collision with player
//        System.out.println("Player: "+(player.worldX+(gp.tileSize - gp.tileSize * 0.75) / 2)+" "+(player.worldY+(gp.tileSize - gp.tileSize * 0.75) / 2));
//        System.out.println("screen: "+screenX+" "+screenY);
//        System.out.println("Player: "+player.worldX+" "+player.worldY);
//        System.out.println("world: "+worldX+" "+worldY);
        if (!isKicked && Math.abs(player.worldX - worldX) < gp.tileSize && Math.abs(player.worldY - worldY) < gp.tileSize) {
            checkMyTeamControl = true;
            // Stick football to the front side of the player
            returnStuct = 1;
            switch (player.direction) {
                case "up":
                    worldX = player.worldX + 10 ;
                    worldY = player.worldY -15- gp.tileSize / 2;
                    break;
                case "down":
                    worldX = player.worldX + 10;
                    worldY = player.worldY -10+ gp.tileSize / 2;
                    break;
                case "left":
                    worldX = player.worldX +10- gp.tileSize / 2;
                    worldY = player.worldY -15+ gp.tileSize / 3;
                    break;
                case "right":
                    worldX = player.worldX +5+ gp.tileSize / 2;
                    worldY = player.worldY -15 + gp.tileSize / 3;
                    break;

                case "up-right":
                    worldX = player.worldX + 15 +gp.tileSize / 3;
                    worldY = player.worldY +15 - gp.tileSize / 3;
                    break;
                case "up-left":
                    worldX = player.worldX +2- gp.tileSize / 3;
                    worldY = player.worldY +15- gp.tileSize / 3;
                    break;
                case "down-right":
                    worldX = player.worldX +10+ gp.tileSize / 3;
                    worldY = player.worldY -5+ gp.tileSize / 3;
                    break;
                case "down-left":
                    worldX = player.worldX +2- gp.tileSize / 3;
                    worldY = player.worldY -5+ gp.tileSize / 3;
                    break;
            }

        }
        else{
            checkMyTeamControl = false;
        }


        double finalX = calculateFinalPositionX(velocityX);
        double finalY = calculateFinalPositionY(velocityY);

//        System.out.println("Final : "+finalX+" "+finalY);


        // Update football's position when kicked

        if (isKicked) {

//            boolean goalDetected = gp.collisionChecker.goalCheck(this);
//            if (goalDetected) {
//                System.out.println("GOAL!");
//                gp.incrementGoalCount(); // Increment goal count in GamePanel
//                gp.resetPositionsAfterGoal(); // Reset positions
//                return 0;
//            }

            myTeamControl = true;
            // Store the current position
            int prevWorldX = worldX;
            int prevWorldY = worldY;

            // Calculate next position
            worldX += velocityX;
            worldY += velocityY;

            // Check for collisions with tiles
            boolean collisionDetected = gp.collisionChecker.checkFootballTile(this);

            if (collisionDetected) {
                // Stop the football if collision occurs
                worldX = prevWorldX;
                worldY = prevWorldY;
                velocityX = 0;
                velocityY = 0;
                myTeamControl = false;
                isKicked = false;
            } else {
                // Slow down the ball over time
                velocityX *= 0.95;
                velocityY *= 0.95;

                // Stop the ball when speed is very low
                if (Math.abs(velocityX) < 0.1 && Math.abs(velocityY) < 0.1) {
                    velocityX = 0;
                    velocityY = 0;
                    myTeamControl = false;
                    isKicked = false;
                }
            }


//        if (isKicked) {
//            myTeamControl = true;
////            gp.collisionChecker.checkFootballCollision(this);
//
//            worldX += velocityX;
//            worldY += velocityY;
////            System.out.println("Football: "+x+" "+y);
////            updateAfterKickX = x;
////            velocityX = (player.direction.equals("left") ? -1 : 1) * Math.abs(speed * 1.5);
//
//            // Slow down the ball over time
//            velocityX *= 0.95;
//            velocityY *= 0.95;
////            System.out.println("Kick Direction: " + player.direction + ", VelocityX: " + velocityX);
////
////            System.out.println("Football: "+velocityX+" "+velocityY);
//
//
//            // Stop the ball when it's slow enough
//            if (Math.abs(velocityX) < 0.1 && Math.abs(velocityY) < 0.1) {
//                isKicked = false;
//
//                myTeamControl = false;
//
//                velocityX = 0;
//                velocityY = 0;
//
//
//                updateAfterKickX = worldX;
//                updateAfterKickY = worldY;
////                System.out.println("football: " + updateAfterKickX + " " + updateAfterKickY);
////                lastFootballX(updateAfterKickX);
////                lastFootballY(updateAfterKickY);
////            UpdateFootball = 1;
//
//
//            }
            if (worldX == 0 && worldY == 0 && !isKicked) {
                System.err.println("Unexpected reset detected!");
                worldX = updateAfterKickX > 0 ? updateAfterKickX : worldX; // Use last valid position
                worldY = updateAfterKickY > 0 ? updateAfterKickY : worldY; // Use last valid position
            }
//            worldX = Math.max(0, Math.min(worldX, gp.screenWidth - gp.tileSize));
//            worldY = Math.max(0, Math.min(worldY, gp.screenHeight - gp.tileSize));

//            if(UpdateFootball == 1){
//                x= updateAfterKickX;
//                y= updateAfterKickY;
//
//                UpdateFootball =0;
//            }

            // Prevent football from going out of bounds (optional boundary logic)
//            if (worldX < 0 || worldX > gp.getScreenWidth() - gp.tileSize) {
//                velocityX = 0;
//                worldX = Math.max(0, Math.min(worldX, gp.getScreenWidth() - gp.tileSize));
//            }
//            if (worldY < 0 || worldY > gp.getScreenHeight() - gp.tileSize) {
//                velocityY = 0;
//                worldY = Math.max(0, Math.min(worldY, gp.getScreenHeight() - gp.tileSize));
//            }
            return 0;
        }
//        else {
//            return 0;
//        }
        return 1;
//            if(UpdateFootball == 1){
//                x= updateAfterKickX;
//                y= updateAfterKickY;
//
//                UpdateFootball =0;
//            }

    }

    public double calculateFinalPositionX(double initialVelocityX) {
        double decayFactor = 0.95;
        double threshold = 0.1;

        // Calculate total distance using the geometric series formula
        double totalDistance = Math.abs(initialVelocityX) >= threshold
                ? initialVelocityX / (1 - decayFactor)
                : 0;

        return worldX + totalDistance; // Add to the initial position
    }

    public double calculateFinalPositionY(double initialVelocityY) {
        double decayFactor = 0.95;
        double threshold = 0.1;

        // Calculate total distance using the geometric series formula
        double totalDistance = Math.abs(initialVelocityY) >= threshold
                ? initialVelocityY / (1 - decayFactor)
                : 0;

        return worldY + totalDistance; // Add to the initial position
    }

//    public double lastFootballX(double updateFootballPositionX){
////        update(controlledPlayer);
//
//        return updateFootballPositionX;
//    }
//    public double lastFootballY(double updateFootballPositionY){

    /// /        update(controlledPlayer);
//        return updateFootballPositionY;
//    }
    public void kick(Player player, boolean upArrow) {

        // Check proximity between player and football
        if (!isKicked && Math.abs(player.worldX - worldX) <= gp.tileSize && Math.abs(player.worldY - worldY) <= gp.tileSize) {
            isKicked = true;

            updateAfterKickX = -1; // Use -1 to indicate that the ball is still moving
            updateAfterKickY = -1;

            // Set the base speed multiplier for the kick
            double baseSpeed = speed * 2.5; // Adjust this factor for stronger kicks
            if(upArrow){
                baseSpeed = speed * 4.5;
            }

            // Calculate velocity based on direction
            double diagonalSpeed = baseSpeed; // Normalize diagonal speed

            // Calculate velocity based on direction
            switch (player.direction) {
                case "up":
                    velocityY = -baseSpeed;
                    velocityX = 0;
                    break;
                case "down":
                    velocityY = baseSpeed;
                    velocityX = 0;
                    break;
                case "left":
                    velocityX = -baseSpeed;
                    velocityY = 0;
                    break;
                case "right":
                    velocityX = baseSpeed;
                    velocityY = 0;
                    break;
                case "up-right":
                    velocityX = diagonalSpeed;
                    velocityY = -diagonalSpeed;
                    break;
                case "up-left":
                    velocityX = -diagonalSpeed;
                    velocityY = -diagonalSpeed;
                    break;
                case "down-right":
                    velocityX = diagonalSpeed;
                    velocityY = diagonalSpeed;
                    break;
                case "down-left":
                    velocityX = -diagonalSpeed;
                    velocityY = diagonalSpeed;
                    break;
            }
            // Adjust the speed for down and right directions
            if (player.direction.equals("down") || player.direction.equals("right")) {
                velocityX *= 1.5; // Increase horizontal speed for "right" direction
                velocityY *= 1.5; // Increase vertical speed for "down" direction
            }
        }
    }

    public void draw(GraphicsContext gc, int checkTeam) {
        double footballWidth = gp.tileSize * 0.5;  // 80% of tile size, adjust as needed
        double footballHeight = gp.tileSize * 0.5;
//        gc.drawImage(up1, x, y, gp.tileSize, gp.tileSize);
//        double offsetX = (gp.tileSize - footballWidth) / 2;
//        double offsetY = (gp.tileSize - footballHeight) / 2;
        double offsetX = (gp.tileSize - footballWidth) / 8;
        double offsetY = (gp.tileSize - footballHeight) ;
//        double offsetX = 0;
//        double offsetY = 0;
        if(checkTeam ==0){
            gc.drawImage(up1, screenX + offsetX, screenY + offsetY, footballWidth, footballHeight);

        } else if (checkTeam==-1) {

            gc.drawImage(up1, screenX + offsetX, screenY + offsetY, footballWidth, footballHeight);

        }


//        if (image != null) {
//            // Scale the player to be smaller than the tile size (e.g., 75% of the tile size)
//            double scale = 0.75; // Adjust this to control how much smaller you want the player
//            double scaledWidth = gp.tileSize * scale;
//            double scaledHeight = gp.tileSize * scale;
//
//            // Center the player image in the tile
//            double offsetX = (gp.tileSize - scaledWidth) / 2;
//            double offsetY = (gp.tileSize - scaledHeight) / 2;
//
//            gc.drawImage(image, screenX + offsetX, screenY + offsetY, scaledWidth, scaledHeight);
//    }
    }
}