package Network;

import com.example.opteam.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class ClientTest {
    private final String serverIP = "192.168.1.103"; // Replace with server's IP
    private final int port;
    private final CountDownLatch connectionLatch;

    public ClientTest(int port, CountDownLatch connectionLatch) {
        this.port = port;
        this.connectionLatch = connectionLatch;
    }

    public void startClient(GamePanel gamePanel) {
        System.out.println("Client Started");
        while (true) {
            try {
                // Attempt to connect to the server
                Socket socket = new Socket(serverIP, port);
                System.out.println("Connected to Server");

                // Initialize streams
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                // Notify that the connection has been established
                connectionLatch.countDown();

                // Start threads for handling server communication
                new ReaderThread(ois, gamePanel); // Updates game state based on server messages
                new WriterThread(oos, gamePanel); // Handles sending messages or updates to the server

                break; // Exit the loop after successful connection
            } catch (IOException e) {
                // Connection failed, retry
                System.err.println("Unable to connect to the server. Retrying...");
                try {
                    Thread.sleep(1000); // Wait 1 second before retrying
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }
}
