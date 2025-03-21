package Entity;

import com.example.game_basic.GamePanel;
import com.example.game_basic.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

public class Player extends entity {
    GamePanel gp;
    KeyHandler keyH;
    public int ID;

    private boolean isControlled = false;
    private int minX, maxX, minY, maxY; // Bounds for auto-move
    private boolean isAutoMoving = false;
    private int targetX, targetY;
    private long lastMoveTime = System.currentTimeMillis();
    private int sleepTime; // Time in milliseconds to wait before the next move
//    public int screenX;
//    public int screenY;
    public String myTeamDirection= "right";

    public Player(GamePanel gp, KeyHandler keyH, int X, int Y, int id, String directionOfPlayer) {
        this.gp = gp;
        this.keyH = keyH;

//        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
//        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
//        screenX = X;
//        screenY = Y;
        worldX = X;
        worldY = Y;

        initialX = X;
        initialY = Y;

        this.ID = id;
        direction = directionOfPlayer;

//        this.x = X;
//        this.y = Y;

        set_default_values();
        getPlayerImage();

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
    }


    @Override
    public String toString() {
        return "Player[" + this.ID + "]";
    }


//    public void setSleepTime(int sleepTime) {
//        this.sleepTime = sleepTime; // Set sleep time for the player
//    }

    public void setBounds(int minX, int maxX, int minY, int maxY,int sleepTime) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.sleepTime = sleepTime;
        setRandomTarget();
    }

    private void setRandomTarget() {
//        targetX = minX + (int) (Math.random() * (maxX - minX + 1));
//        targetY = minY + (int) (Math.random() * (maxY - minY + 1));

        targetX = Math.max(minX, Math.min(maxX, gp.football.worldX));

        // Clamp footballY within the vertical bounds
        targetY = Math.max(minY, Math.min(maxY, gp.football.worldY));
    }

    public void setControlled(boolean controlled) {
        isControlled = controlled;
        isAutoMoving = !controlled;
    }

    public void autoMove() {
        if (isControlled) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastMoveTime < sleepTime) {
            return; // Wait until sleep time has passed
        }

        int dx = targetX - worldX;
        int dy = targetY - worldY;
        double distance = Math.hypot(dx, dy);

        if (distance < speed) {
            lastMoveTime = currentTime;
            setRandomTarget(); // Reached target, choose a new one

        } else {

            // Calculate the direction
            direction = getDirection(dx, dy);
//            System.out.println("Player direction: " + direction);

            worldX += (int) (dx / distance * speed);
            worldY += (int) (dy / distance * speed);

        }
    }

    private String getDirection(int dx, int dy) {
        if (dx > 0 && dy > 0) {
            return "down-right";
        } else if (dx > 0 && dy < 0) {
            return "up-right";
        } else if (dx < 0 && dy > 0) {
            return "down-left";
        } else if (dx < 0 && dy < 0) {
            return "up-left";
        } else if (dx > 0) {
            return "right";
        } else if (dx < 0) {
            return "left";
        } else if (dy > 0) {
            return "down";
        } else if (dy < 0) {
            return "up";
        } else {
            return "stationary"; // No movement
        }
    }


    public void set_default_values() {
//        worldX = gp.tileSize * 23;
//        worldY = gp.tileSize * 21;
        speed = 3;
//        direction = "right";
    }

    public void resetPosition() {
        // Set player's position to the initial values
        worldX = initialX;
        worldY = initialY;
        direction = "right"; // Default direction
    }

    public void resetForOnlyOnePlayer(int x, int y){
        worldX = x;
        worldY = y;
    }


    public void getPlayerImage() {
        up1 = new Image(getClass().getResourceAsStream("/player/boy_up_1.png"));
        up2 = new Image(getClass().getResourceAsStream("/player/boy_up_2.png"));
        down1 = new Image(getClass().getResourceAsStream("/player/boy_down_1.png"));
        down2 = new Image(getClass().getResourceAsStream("/player/boy_down_2.png"));
        left1 = new Image(getClass().getResourceAsStream("/player/boy_left_1.png"));
        left2 = new Image(getClass().getResourceAsStream("/player/boy_left_2.png"));
        right1 = new Image(getClass().getResourceAsStream("/player/boy_right_1.png"));
        right2 = new Image(getClass().getResourceAsStream("/player/boy_right_2.png"));
    }

    public void update(Football football) {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {


            int prevWorldX = worldX;
            int prevWorldY = worldY;

            collission = false;

//            if (Math.abs(football.worldX - worldX) < gp.tileSize && Math.abs(football.worldY - worldY) < gp.tileSize) {
//
//                if (keyH.upPressed && keyH.rightPressed) {
//                    direction = "up-right";
//                    worldY -= 5;
//                    worldX += 5;
//                } else if (keyH.upPressed && keyH.leftPressed) {
//                    direction = "up-left";
//                    worldY -= 5;
//                    worldX -= 5;
//                } else if (keyH.downPressed && keyH.rightPressed) {
//                    direction = "down-right";
//                    worldY += 5;
//                    worldX += 5;
//                } else if (keyH.downPressed && keyH.leftPressed) {
//                    direction = "down-left";
//                    worldY += 5;
//                    worldX -= 5;
//                }
//
//
//            }


            /// //////diagonal>>>>
            if (keyH.upPressed && keyH.rightPressed) {
                direction = "up-right";
                worldY -= speed;
                worldX += speed;

            } else if (keyH.upPressed && keyH.leftPressed) {
                direction = "up-left";
                worldY -= speed;
                worldX -= speed;

            } else if (keyH.downPressed && keyH.rightPressed) {
                direction = "down-right";
                worldY += speed;
                worldX += speed;

            } else if (keyH.downPressed && keyH.leftPressed) {
                direction = "down-left";
                worldY += speed;
                worldX -= speed;

            }

            /// //////single direction>>>>
            else if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;


            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;

            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;

            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;

            }


//            if (screenX != 0 && screenY != 0) {
//                screenX = (int) (screenX / Math.sqrt(2));
//                screenY = (int) (screenY / Math.sqrt(2));
//            }

//             Check horizontal collisions
//            if (horizontalMovement != 0) {
//                int prevWorldX = worldX;
////                worldX += horizontalMovement;
//                collission = false;
//                gp.collisionChecker.checkTile(this);
//                if (collission) worldX = prevWorldX;
//            }
            gp.collisionChecker.checkTile(this);
            if (collission) {
                worldX = prevWorldX;
                worldY = prevWorldY;
            }
//
//            // Check vertical collisions
//            if (screenY != 0) {
//                int prevWorldY = worldY;
//                worldY += screenY;
//                collission = false;
//                gp.collisionChecker.checkTile(this);
//                if (collission) worldY = prevWorldY;
//            }

            // Update direction for facing
//            if (screenX > 0) direction = "right";
//            else if (screenX < 0) direction = "left";
//            else if (screenY < 0) direction = "up";
//            else if (screenY > 0) direction = "down";

            // Update sprite animation
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }


//    public void draw(GraphicsContext gc) {
//        Image image = null;
//        gc.getPixelWriter().getPixelFormat();
//        gc.setImageSmoothing(false);
//
//        switch (direction){
//            case "up":
//                if(spriteNum == 1){
//                    image = up1;
//                }
//                if(spriteNum == 2){
//                    image = up2;
//                }
//                break;
//            case "down":
//                if(spriteNum == 1){
//                    image = down1;
//                }
//                if(spriteNum == 2){
//                    image = down2;
//                }
//                break;
//            case "left":
//                if(spriteNum == 1){
//                    image = left1;
//                }
//                if(spriteNum == 2){
//                    image = left2;
//                }
//                break;
//            case "right":
//                if(spriteNum == 1){
//                    image = right1;
//                }
//                if(spriteNum == 2){
//                    image = right2;
//                }
//                break;
//        }
//
//        if (image != null) {

    /// /            gc.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize);
//            gc.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize);
//        }
//    }
    public void draw(GraphicsContext gc) {
        Image image = null;

        gc.getPixelWriter().getPixelFormat();
        // Disable image smoothing
        gc.setImageSmoothing(false);

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            case "up-right":
                image = (spriteNum == 1) ? right1 : right2; // Reuse right sprite
                break;
            case "up-left":
                image = (spriteNum == 1) ? left1 : left2; // Reuse left sprite
                break;
            case "down-right":
                image = (spriteNum == 1) ? right1 : right2; // Reuse right sprite
                break;
            case "down-left":
                image = (spriteNum == 1) ? left1 : left2; // Reuse left sprite
                break;
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
////            gc.drawImage(image, screenX + offsetX, screenY + offsetY, scaledWidth, scaledHeight);
//            gc.drawImage(image, worldX + offsetX, worldY + offsetY, scaledWidth, scaledHeight);
//        }
        if (image != null) {
            // Map world coordinates to screen coordinates
            int screenX = worldX - gp.football.worldX + gp.football.screenX;
            int screenY = worldY - gp.football.worldY + gp.football.screenY;

            // Scale the player to be smaller than the tile size
            double scale = 0.75; // Adjust this as needed
            double scaledWidth = gp.tileSize * scale;
            double scaledHeight = gp.tileSize * scale;
            double offsetX = (gp.tileSize - scaledWidth) / 2;
            double offsetY = (gp.tileSize - scaledHeight) / 2;

            gc.drawImage(image, screenX + offsetX, screenY + offsetY, scaledWidth, scaledHeight);
        }

    }

    public int getId() {
        return this.ID;
    }
}
