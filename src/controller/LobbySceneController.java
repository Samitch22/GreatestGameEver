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
public class LobbySceneController implements Initializable {

    private ClientProtocol clientProtocol;
    
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
        try {
            System.out.println("Connecting to server...");
            clientProtocol = new ClientProtocol();
            clientProtocol.test();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
            Platform.runLater(() -> {
                try {
                    LobbySceneController.this.handleBtnCancelAction(null);
                } catch (IOException ex1) {
                    System.out.println("Unexpected Exception: " + ex1.getMessage());
                }
            });
        }
    }    
    
    /**
     * Returns the player to the start scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    protected void handleBtnCancelAction(ActionEvent event) throws IOException {
        System.out.println("Matchmaking canceled.");
        Parent pane = FXMLLoader.load(getClass().getResource("/view/StartScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
