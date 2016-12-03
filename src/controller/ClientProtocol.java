/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client protocol that implements multiplayer gameplay. 
 * @author Mitchell
 */
public class ClientProtocol {
    
    private static final Character   QUIT = 'Q';
    private final String             ipAddress = "localhost";
    private final int                port = 8080;
    private final Socket             clientSocket;
    private final ObjectInputStream  inFromServer;
    private final ObjectOutputStream outToServer;
    
    /**
     * Constructs the protocol for multiplayer gameplay.
     * @throws IOException
     */
    public ClientProtocol() throws IOException {
        clientSocket = new Socket(ipAddress, port);
        outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        
        // FUTURE
        // Disconnects from the server when the application is exited.
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                try {
//                    outToServer.writeObject(ClientProtocol.QUIT);
//                    System.out.println("Disconnected from the server.");
//                } catch (IOException e) { 
//                    System.out.println("Unexpected Exception: " + e.getMessage()); }
//        }});
    }
    
    /**
     * Tests connection to server.
     */
    public void test() {
        try {
            String str = "Hello server!";
            outToServer.writeObject(str);
            System.out.println("Sent to server.");
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
}
