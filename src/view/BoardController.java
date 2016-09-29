/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Board;

/**
 *
 * @author Mitchell
 */
public class BoardController implements Initializable {

    private Board      board;
    private Object[][] boardGrid;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane gpBoard;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            generateBoard();
            startGame();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Generate the board.
     */
    @FXML
    private void generateBoard() throws IOException {
        // Load a 2D array to the grid

        board = new Board();
        boardGrid = board.getBoard();

        // Load the board into the grid pane as buttons
        for ( int r = 0; r < Board.getBoardSize(); r++ ) {
            for ( int c = 0; c < Board.getBoardSize(); c++ ) {
                String text = boardGrid[r][c].toString();
                System.out.println(text);
                this.gpBoard.add(new Button(text), c, r);
                //Button temp = (Button) this.getGridNode(r, c);
                //temp.setText(text);
            }
        }      
    }
    
    /**
     * Start the game.
     */
    public void startGame() {
        
    }
    
    /**
     * Possibly not needed.
     * @param row
     * @param col
     * @return
     * @throws IOException 
     */
    @FXML
    private Node getGridNode(int row, int col) throws IOException {
        Node returnNode = null;
        ObservableList<Node> children = this.gpBoard.getChildren();

        for ( Node node : children ) {
            if(GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == col) {
                returnNode = node;
                break;
            }
        }

        return returnNode;
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    private void showGameoverScene(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("GameoverScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
}
