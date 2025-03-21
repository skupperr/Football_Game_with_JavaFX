package Network;

import com.example.game_basic.GamePanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ServerClient {
    static final List<ObjectOutputStream> clientOutputStreams = Collections.synchronizedList(new ArrayList<>());
    private final ServerSocket serverSocket;
    private final CountDownLatch connectionLatch;

    // Constructor to initialize the server with a latch
    public ServerClient(int port, CountDownLatch connectionLatch) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.connectionLatch = connectionLatch;
        System.out.println("Server Started on port " + port);
    }

    // Method to continuously wait for client connections
    public void waitForClients(GamePanel gamePanel) {
        new Thread(() -> {
            try {
                while (true) { // Continuously accept new clients
                    System.out.println("Waiting for clients to connect...");
                    Socket socket = serverSocket.accept(); // Block until a client connects
                    System.out.println("A client connected!");

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    synchronized (clientOutputStreams) {
                        clientOutputStreams.add(oos); // Add client stream to the list
                    }

                    // Start a thread to handle the connected client
                    new ServerThread(socket, oos, gamePanel);

                    connectionLatch.countDown(); // Notify that at least one client has connected
                }
            } catch (IOException e) {
                System.out.println("Error waiting for clients: " + e.getMessage());
            }
        }).start();
    }

    // Broadcast a message to all connected clients
    public static void broadcastMessage(Object message, ObjectOutputStream senderStream) {
        synchronized (clientOutputStreams) {
            Iterator<ObjectOutputStream> iterator = clientOutputStreams.iterator();
            while (iterator.hasNext()) {
                ObjectOutputStream oos = iterator.next();
                if (oos != senderStream) { // Avoid sending the message back to the sender
                    try {
                        oos.writeObject(message);
                        oos.flush();
                    } catch (IOException e) {
                        System.out.println("Error broadcasting message: " + e.getMessage());
                        iterator.remove(); // Remove the disconnected client
                    }
                }
            }
        }
    }

    // Close the server socket
    public void closeServer() throws IOException {
        serverSocket.close();
    }
}

class ServerThread implements Runnable {
    private final Socket clientSocket;
    private final ObjectOutputStream oos;
    private final GamePanel gamePanel;

    public ServerThread(Socket clientSocket, ObjectOutputStream oos, GamePanel gamePanel) {
        this.clientSocket = clientSocket;
        this.oos = oos;
        this.gamePanel = gamePanel;

        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            new ReaderThread(ois, gamePanel); // Use the same ObjectInputStream
        } catch (IOException e) {
            System.out.println("Error initializing ReaderThread: " + e.getMessage());
        }

        new ServerWriter(oos, gamePanel);
    }


    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
            while (true) {
                // Receive GameState or other objects from the client
                Object receivedObject = ois.readObject();
                if (receivedObject instanceof GameState) {
                    GameState gameState = (GameState) receivedObject;
                    System.out.println("Received GameState from client");

                    // Broadcast GameState to all clients
                    ServerClient.broadcastMessage(gameState, oos);
                } else {
                    System.out.println("Received unknown object: " + receivedObject);
                }
            }
        } catch (Exception e) {
            System.out.println("Client disconnected: " + clientSocket.getInetAddress());
            synchronized (ServerClient.clientOutputStreams) {
                ServerClient.clientOutputStreams.remove(oos); // Remove client stream
            }
        }
    }
}
