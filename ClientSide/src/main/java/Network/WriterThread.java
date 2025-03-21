package Network;

import com.example.opteam.GamePanel;

import java.io.ObjectOutputStream;

class WriterThread implements Runnable {
    private final ObjectOutputStream oos;
    private GamePanel gamePanel;

    public WriterThread(ObjectOutputStream oos, GamePanel gamePanel) {
        this.oos = oos;
        this.gamePanel = gamePanel;
        System.out.println("Client Writer initialized with GamePanel instance: " + gamePanel);

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
//                System.out.println("In clientWriter: " + gameState);

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
