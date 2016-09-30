/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Board;
import model.Player;

/**
 *
 * @author Mitchell
 */
public class BoardController implements Initializable {
    
    private Board      board;
    private Player     player;
    private Object[][] boardGrid;
    private List<Node> selected;
    //private Object[][] selectedGrid;
    private String     selectedWord;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane gpBoard;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            player = new Player();
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

        board = new Board(player);
        boardGrid = board.getBoard();
        selected = new ArrayList<>();
        selectedWord = "";
        
        // Load the board into the grid pane as buttons
        for ( int r = 0; r < Board.getBoardSize(); r++ ) {
            for ( int c = 0; c < Board.getBoardSize(); c++ ) {
                String text = boardGrid[r][c].toString();
                Button temp = new Button(text);
                this.gpBoard.add(temp, c, r);
                temp.setBackground(Background.EMPTY);
                temp.setFont(Font.font(24.0));
                temp.setAlignment(Pos.CENTER);
                temp.setMinWidth(55);
                temp.setOnAction( this::handleSelected );
            }
        }      
    }
    
    /**
     * Start the game.
     */
    @FXML
    private void startGame() {
        player.setBoard(board);
        player.startGame();
    }
    
    /**
     * Gets the node located at the given coordinates.
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
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            if ( r != null && c != null ) {
                if( r == row && c == col ) {
                    returnNode = node;
                    break;
                }
            }
        }

        return returnNode;
    }
    
    /**
     * 
     */
    @FXML
    private void handleSelected(ActionEvent event) {
        Button selectedButton = (Button)event.getSource();
        
        if ( this.validateClick(selectedButton) == false ) {
            resetSelection();
        }
        
        String text = selectedButton.getText();
        selectedWord += text;
        selected.add(selectedButton);
        selectedButton.setStyle("-fx-border: 12px solid; -fx-border-color: green;");        
        System.out.println(selectedWord);
    }
    
    /**
     * 
     * @param button
     * @return 
     */
    private boolean validateClick(Node b) {
        int r = getR(b);
        int c = getC(b);
        int nextTo = 1;
        
        if ( selectedWord.isEmpty() )
            return true;
        for ( Node btn : selected ) {
            if ( (getR(btn) + nextTo) == r || (getR(btn) - nextTo) == r || getR(btn) == r ) { 
                if ( (getC(btn) + nextTo) == c || (getC(btn) - nextTo) == c || getC(btn) == c )
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 
     * @param button
     * @return 
     */
    private int getR(Node b) {
        return GridPane.getRowIndex(b);
    }
    
    /**
     * 
     * @param button
     * @return 
     */
    private int getC(Node b) {
        return GridPane.getColumnIndex(b);
    }
    
    /**
     * 
     */
    private void resetSelection() {
        for ( Node b : selected ) {
            b.setStyle("");
        }
        selected.clear();
        selectedWord = "";
    }
    
    /**
     * 
     * @return 
     */
    private boolean checkWord() {
        return false;
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showGameoverScene(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/view/GameoverScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    
    
    /**************************************************************************
    /**
     * Not Needed
     * @param gridPane 
     */
//    @FXML
//    private void getGridChildren(GridPane gridPane) {
//
//        ObservableList<Node> children = gridPane.getChildren();
//        gridChildren = new Node[Board.getBoardSize()][Board.getBoardSize()];
//
//        int r;
//        int c;
//        for ( Node node : children ) {
//            if ( node instanceof Button ) {
//                System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
//            }
//            r = GridPane.getRowIndex(node);
//            c = GridPane.getColumnIndex(node);
//            gridChildren[r][c] = node;
//            System.out.println(Arrays.toString(gridChildren));
//        }
//
//    }
    
    
    
    
    
}
