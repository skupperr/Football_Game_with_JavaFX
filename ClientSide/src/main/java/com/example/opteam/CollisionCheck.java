package com.example.opteam;

import Entity.Football;
import Entity.entity;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(entity entity) {
        // Get current entity boundaries
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int tileNum1, tileNum2;

        // Check for vertical collisions (up/down)
        if (entity.direction.contains("up") || entity.direction.contains("down")) {
            int entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
            int entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            int entityLeftCol = entityLeftWorldX / gp.tileSize;
            int entityRightCol = entityRightWorldX / gp.tileSize;

            if (entity.direction.contains("up")) {
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityTopRow];
            } else {
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
            }

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collission = true;
            }
        }

        // Check for horizontal collisions (left/right)
        if (entity.direction.contains("left") || entity.direction.contains("right")) {
            int entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            int entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            int entityTopRow = entityTopWorldY / gp.tileSize;
            int entityBottomRow = entityBottomWorldY / gp.tileSize;

            if (entity.direction.contains("left")) {
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
            } else {
                tileNum1 = gp.tileM.mapTile[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
            }

            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collission = true;
            }
        }
    }



    public boolean checkFootballTile(Football football) {
        boolean collisionDetected = false;

        // Calculate football boundaries
        int leftWorldX = football.worldX + football.solidArea.x;
        int rightWorldX = football.worldX + football.solidArea.x + football.solidArea.width;
        int topWorldY = football.worldY + football.solidArea.y;
        int bottomWorldY = football.worldY + football.solidArea.y + football.solidArea.height;

        // Determine tile positions
        int leftCol = leftWorldX / gp.tileSize;
        int rightCol = rightWorldX / gp.tileSize;
        int topRow = topWorldY / gp.tileSize;
        int bottomRow = bottomWorldY / gp.tileSize;

        // Check tiles for collision
        int tileNum1, tileNum2;

        // Check top side
        if (football.velocityY < 0) {
            tileNum1 = gp.tileM.mapTile[leftCol][topRow];
            tileNum2 = gp.tileM.mapTile[rightCol][topRow];
            if (gp.tileM.tile[tileNum1].football_collision || gp.tileM.tile[tileNum2].football_collision) {
                collisionDetected = true;
            }
        }

        // Check bottom side
        if (football.velocityY > 0) {
            tileNum1 = gp.tileM.mapTile[leftCol][bottomRow];
            tileNum2 = gp.tileM.mapTile[rightCol][bottomRow];
            if (gp.tileM.tile[tileNum1].football_collision || gp.tileM.tile[tileNum2].football_collision) {
                collisionDetected = true;
            }
        }

        // Check left side
        if (football.velocityX < 0) {
            tileNum1 = gp.tileM.mapTile[leftCol][topRow];
            tileNum2 = gp.tileM.mapTile[leftCol][bottomRow];
            if (gp.tileM.tile[tileNum1].football_collision || gp.tileM.tile[tileNum2].football_collision) {
                collisionDetected = true;
            }
        }

        // Check right side
        if (football.velocityX > 0) {
            tileNum1 = gp.tileM.mapTile[rightCol][topRow];
            tileNum2 = gp.tileM.mapTile[rightCol][bottomRow];
            if (gp.tileM.tile[tileNum1].football_collision || gp.tileM.tile[tileNum2].football_collision) {
                collisionDetected = true;
            }
        }

        football.football_collision = collisionDetected; // Update football's collision flag
        return collisionDetected;
    }

    public boolean goalCheck(Football football,GamePanel gamePanel) {
        int footballTileX = football.worldX / gp.tileSize;
        int footballTileY = football.worldY / gp.tileSize;

        // Check if the current tile has a goal collision
        if (gp.tileM.tile[gp.tileM.mapTile[footballTileX][footballTileY]].football_goal_collision) {
            new Thread(() -> {
                try {
                    // Wait for 2 seconds
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int tileNum = gp.tileM.mapTile[footballTileX][footballTileY];
                if(tileNum == 649 || tileNum ==650 || tileNum == 651 ||tileNum == 692 || tileNum ==693 || tileNum == 694 ||tileNum == 735 || tileNum == 736 || tileNum == 737){
                    gamePanel.checkWhichSideGoal = true;
                }
                else{
                    gamePanel.checkWhichSideGoal = false;
                }
                // Reset football position
                football.setDefaultValues(1134, 864);
                football.velocityX =0;
                football.velocityY =0;
                gamePanel.GoalCheckForBoth = true;

            }).start();
            return true;
        }
        else{
            gamePanel.GoalCheckForBoth = false;

        }
        return false;
    }


//    public boolean goalCheck(Football football) {
//        int footballTileX = football.worldX / gp.tileSize;
//        int footballTileY = football.worldY / gp.tileSize;
//
//        // Check if the current tile has a goal collision
//        if (gp.tileM.tile[gp.tileM.mapTile[footballTileX][footballTileY]].football_goal_collision) {
////            System.out.println(footballTileX+" "+footballTileY);
//            football.setDefaultValues(1134,864);
//            return true;
//        }
//        return false;
//    }

    public boolean goalPositionCheck(Football football) {
        int footballTileX = football.worldX / gp.tileSize;
        int footballTileY = football.worldY / gp.tileSize;

        // Check if the current tile has a goal collision
        if (gp.tileM.tile[gp.tileM.mapTile[footballTileX][footballTileY]].football_goal_collision) {
            return true;
        }
        return false;
    }







}
