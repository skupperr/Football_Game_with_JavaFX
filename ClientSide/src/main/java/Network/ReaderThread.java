package Network;

import Network.GameState;
import com.example.opteam.*;
import javafx.application.Platform;

import java.io.ObjectInputStream;

public class ReaderThread extends Thread {
    private ObjectInputStream ois;
    private GamePanel gamePanel;

    public ReaderThread(ObjectInputStream ois, GamePanel gamePanel) {
        this.ois = ois;
        this.gamePanel = gamePanel;
        start();
    }

    public void run() {
        try {
            while (true) {
                // Receive game state from the server
                GameState gameState = (GameState) ois.readObject();
//                System.out.println("GameState received: " + gameState);

                // Update the game state in the GamePanel
                Platform.runLater(() -> gamePanel.applyGameState(gameState)); // Ensure thread safety
            }
        } catch (Exception e) {
            System.out.println("Error in ReaderThread: " + e.getMessage());
        }
    }
}