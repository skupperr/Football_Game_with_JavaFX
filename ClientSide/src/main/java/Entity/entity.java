package Entity;

import javafx.scene.image.Image;

import java.awt.*;

public class entity {
    public int worldX, worldY, initialX,initialY;
    public int speed;
    public int x, y;
    public int updateAfterKickX, updateAfterKickY;
    public boolean myTeamControl= false;

    public Image footballImage,up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum  = 1;

    public Rectangle solidArea;
    public boolean collission = false;
    public boolean football_collision = false;
}
