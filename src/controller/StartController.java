/*
 * The Greatest Game Ever
 * The Other Guys
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller
 * Starting scene for a player. A player chooses the gameplay desired.
 * @author Mitchell
 */
public class StartController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
    }    
    
    /**
     * Starts the game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnStartAction(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
        Parent pane = FXMLLoader.load(getClass().getResource("/view/BoardScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    /**
     * Starts a multiplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMultiplayerAction(ActionEvent event) throws IOException {
        System.out.println("Starting multiplayer!");
        Parent pane = FXMLLoader.load(getClass().getResource("/view/LobbyScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
