/*
 * The Greatest Game Ever
 * The Other Guys
 */
package view;

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
 *
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
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnStartAction(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
        
        Parent pane = FXMLLoader.load(getClass().getResource("BoardScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    
    
    
    
//    /**
//     * Displays the Board Scene.
//     */
//    private void showBoardScene() throws IOException {
//        Scene scene = btnStart.getScene();
//        getController();
//        StackPane boardScene = new StackPane(this.root);
//        //generateBoard();
//        //boardScene.getChildren().add(this.getController().vbContent);
//        scene.setRoot(boardScene);
//    }
//    
//    private void showGameoverScene() throws IOException {
//        Scene scene = txtTarget.getScene();
//        this.gameoverLayout = FXMLLoader.load(getClass().getResource("GameoverScene.fxml"));
//        StackPane gameoverScene = new StackPane(this.boardLayout);
//        scene.setRoot(gameoverScene);
//    }
//
//    public StartController getController() throws IOException {
//        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardScene.fxml"));
//        loader.setController(this);
//        root = (Parent) loader.load();
//        controller = loader.getController();
//        
//        return controller;
//    }
    
    
}
