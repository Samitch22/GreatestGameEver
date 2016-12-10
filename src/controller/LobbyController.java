/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 * A lobby scene for a player waiting for a multiplayer game to start.
 * @author Mitchell
 */
public class LobbyController implements Initializable {

    private static ClientProtocol clientProtocol;
    private static boolean emergencyStop = false;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label lblDisplay;
    @FXML
    private Button btnCancel;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emergencyStop = false;
        try {
            System.out.println("Connecting to server...");
            clientProtocol = new ClientProtocol();
            Platform.runLater(() -> {
                try {
                    clientProtocol.receiveBoard();
                    this.showBoardScene(null);
                } catch (IOException ex) {
                    System.out.println("Unexpected Exception: " + ex.getMessage());
                    try {
                        this.handleBtnCancelAction(null);
                    } catch (IOException ex1) {
                        System.out.println("Unexpected Exception: " + ex1.getMessage());
                        System.exit(-1);
                    }
                }
            }); 
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
            LobbyController.emergencyStop = true;
            try {
               this.handleBtnCancelAction(null);
            } catch (IOException ex1) {
                System.out.println("Unexpected Exception: " + ex1.getMessage());
                System.exit(-1);
            }
        }
//        if ( LobbyController.emergencyStop == false ) {
//            try {
//                this.showLobbyScene(null);
//            } catch (IOException ex) {
//                System.out.println("Unexpected Exception: " + ex.getMessage());
//            }
//        }
    }    

    public static ClientProtocol getClientProtocol() {
        return clientProtocol;
    }

    /**
     * Gets the emergency stop state.
     * @return
     */
    public static boolean isEmergencyStop() {
        return emergencyStop;
    }
    
    /**
     * Returns the player to the start scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void handleBtnCancelAction(ActionEvent event) throws IOException {
        System.out.println("Matchmaking canceled.");
        Parent pane = FXMLLoader.load(getClass().getResource("/view/StartScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    /**
     * Shows the board scene to start a multiplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showBoardScene(ActionEvent event) throws IOException {
        System.out.println("Starting a multiplayer game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        while (LobbyController.getClientProtocol().getBoard() == null ) {}
        BoardController bc = loader.<BoardController>getController();
        bc.startMultiplayer();
        
    }
    
}
