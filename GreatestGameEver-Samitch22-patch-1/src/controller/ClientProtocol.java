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
import model.Board;
import model.Player;

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
    private       Board              board;
    /**
     * Constructs the protocol for multiplayer gameplay.
     * @throws IOException
     */
    public ClientProtocol() throws IOException {
        clientSocket = new Socket(ipAddress, port);
        outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        System.out.println("Connected to server!");
        
        // Disconnects from the server when the application is exited.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    outToServer.writeObject(ClientProtocol.QUIT);
                    System.out.println("Disconnected from the server.");
                } catch (IOException e) { 
                    System.out.println("Unexpected Exception: " + e.getMessage()); }
        }});
    }
    
    /**
     * Notifies the server the client wants to disconnect.
     * @throws IOException
     */
    public void disconnect() throws IOException {
        outToServer.writeChar(ClientProtocol.QUIT);
    }
    
    /**
     * Gets the board from the server.
     * @throws IOException
     */
    public void receiveBoard() throws IOException {
        while ( board == null ) {
            try {
                //board = (Board) inFromServer.readObject();
                Player p = (Player) inFromServer.readObject();
            } catch (ClassNotFoundException ex) {
                System.out.println("Class not found: " + ex.getMessage());
            }
        }
        
    }

    /**
     * Gets the board.
     * @return
     */
    public Board getBoard() {
        return board;
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
    
    public void testCP() {
        System.out.println("Testing Client Protocol object.");
    }
}
