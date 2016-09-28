/*
 * The Greatest Game Ever
 * The Other Guys
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Board;

/**
 *
 * @author Mitchell
 */
public class FXMLDocumentController implements Initializable {
    
    private Parent     boardLayout;
    private Parent     gameoverLayout;
    private Parent     root;
    private Board      board;
    private Object[][] boardGrid;
    private FXMLDocumentController controller;
    
//        this.gameoverLayout = FXMLLoader.load(getClass().getResource("GameoverScene.fxml"));
    
    @FXML
    private Label     label;
    @FXML
    private Button    btnStart;
    @FXML
    private GridPane  gpBoard;
    @FXML
    private Button    letterButton;
    @FXML
    private TextField txtTarget;
    @FXML
    private VBox      vbContent;
    
    @FXML
    private void handleBtnStartAction(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
//        generateBoard();
        
        showBoardScene();
        //this.gpBoard.add(new Button("T"), 0, 0);
        generateBoard();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    /**
     * 
     */
    @FXML
    private void generateBoard() throws IOException {
        // Load a 2D array to the grid
        this.gpBoard.add(new Button("T"), 0, 0);
        board = new Board();
        boardGrid = board.getBoard();
        System.out.println(getController().gpBoard.toString());
        this.getController().vbContent.getChildren().remove(this.gpBoard);
        this.getController().gpBoard.getChildren().clear();
        GridPane test = new GridPane();
        // Load the board into the grid pane as buttons
        for ( int r = 0; r < Board.getBoardSize(); r++ ) {
            for ( int c = 0; c < Board.getBoardSize(); c++ ) {
                System.out.println(boardGrid[r][c].toString());
//                this.getController().gpBoard.add(new Button(boardGrid[r][c].toString()), c, r);
                Button temp = new Button("T");
                test.add(temp, c, r);
                //this.getController().gpBoard.add(new Button("T"), c, r);
                //this.getController().gpBoard.getChildren().add(root);
            }
        }
        this.getController().gpBoard.autosize();
        this.getController().vbContent.getChildren().add(test);
        //this.getController().vbContent.getChildren().add(this.getController().gpBoard);
        
    }
    
    /**
     * Displays the Board Scene.
     */
    private void showBoardScene() throws IOException {
        Scene scene = btnStart.getScene();
        getController();
        StackPane boardScene = new StackPane(this.root);
        //generateBoard();
        //boardScene.getChildren().add(this.getController().vbContent);
        scene.setRoot(boardScene);
    }
    
    private void showGameoverScene() throws IOException {
        Scene scene = txtTarget.getScene();
        this.gameoverLayout = FXMLLoader.load(getClass().getResource("GameoverScene.fxml"));
        StackPane gameoverScene = new StackPane(this.boardLayout);
        scene.setRoot(gameoverScene);
    }

    public FXMLDocumentController getController() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardScene.fxml"));
        root = (Parent) loader.load();
        controller = loader.getController();
        
        return controller;
    }
    
}
