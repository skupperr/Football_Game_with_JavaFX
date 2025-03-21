package Network;

import com.example.game_basic.GamePanel;

import java.io.ObjectOutputStream;

class ServerWriter implements Runnable {
    private final ObjectOutputStream oos;
    private GamePanel gamePanel;

    public ServerWriter(ObjectOutputStream oos, GamePanel gamePanel) {
        this.oos = oos;
        this.gamePanel = gamePanel;
        System.out.println("ServerWriter initialized with GamePanel instance: " + gamePanel);

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                GameState gameState;

                // Synchronize access to gamePanel
                synchronized (gamePanel) {
                    gameState = gamePanel.generateGameState();
                }

                // Debug: print the generated GameState
//                System.out.println("In ServerWriter: " + gameState);

                // Reset and send the updated GameState to the client
                oos.reset();
                oos.writeObject(gameState);
                oos.flush();

                Thread.sleep(10); // Adjust interval as needed
            }
        } catch (Exception e) {
            System.out.println("Error in ServerWriter: " + e.getMessage());
        }
    }




}
