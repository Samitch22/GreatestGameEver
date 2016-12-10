/*
 * The Greatest Game Ever
 * The Other Guys
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
         
    }    
    
    /**
     * Starts a singleplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnStartAction(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startSingleplayer();
    }
    
    /**
     * Starts a multiplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMultiplayerAction(ActionEvent event) throws IOException {
        System.out.println("Starting multiplayer!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LobbyScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);
        //LobbyController.getClientProtocol().receiveBoard();
//        if ( LobbyController.isEmergencyStop() == false ) {
//            new Thread ( () -> {
//                try {
//                    LobbyController.getClientProtocol().receiveBoard();
//                } catch (IOException ex) {
//                    System.out.println("Unexpected Exception: " + ex.getMessage());
//                    Platform.runLater(() -> {
//                        try {
//                            loader.<LobbyController>getController().handleBtnCancelAction(null);
//                        } catch (IOException ex1) {
//                            System.out.println("Unexpected Exception: " + ex1.getMessage());
//                        }
//                    });
//                        
//
//                }
//            }).start();
        //}
    }
}
