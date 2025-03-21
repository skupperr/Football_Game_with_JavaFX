package Network;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    public List<PlayerData> myPlayers;
    public List<PlayerData> opPlayers;
    public int footballX, footballY;
    public boolean kickFootball=false ,myTeamControl=false;
    public double velocityX = 0;
    public double velocityY = 0;
    public int ServerGoal = 0;
    public int ClientGoal = 0;
    public String SplayerName ;
    public String OplayerName ;

    public static class PlayerData implements Serializable {
        public int id;
        public int x, y;
        public String direction = "right";

        @Override
        public String toString() {
            return "PlayerData{id=" + id + ", x=" + x + ", y=" + y + "}";
        }
    }

    @Override
    public String toString() {
        return "GameState{" +
                "myPlayers=" + myPlayers +
                ", opPlayers=" + opPlayers +
                ", footballX=" + footballX +
                ", footballY=" + footballY +
                '}';
    }

}
