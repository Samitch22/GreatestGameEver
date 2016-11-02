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
import java.util.TimerTask;
import javafx.application.Platform;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import model.Board;
import model.Player;
import model.Word;

/**
 * This class controls the UI functionality of the board.
 * @author Mitchell
 */
public class BoardController extends TimerTask implements Initializable {
    
    private Board        board;
    private Player       player;
    private Object[][]   boardGrid;
    private List<Node>   selected;
    private Object[][][] wordKey;
    private Object[]     targetKeys;
    private int          btnKey;
    private static BoardController bc;
    private final Media buzzer  = new Media( getClass().getClassLoader().getResource("files/Buzzer.wav").toExternalForm());
    private final Media correct = new Media( getClass().getClassLoader().getResource("files/Correct.wav").toExternalForm());
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane gpBoard;
    
    @Override
    public void run() {
        System.out.println("Time's up!");
        Platform.runLater(() -> {
            bc.resetSelection();
            playSound(this.buzzer);
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            bc = this;
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
        wordKey = board.getWordKeys();
        targetKeys = board.getTargetKeys();
        
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
        board.startGame();
    }
    
    /**
     * This method handles when a letter button is selected.
     */
    @FXML
    private void handleSelected(ActionEvent event) {
        Button selectedButton = (Button)event.getSource();
        int first = 1;
        int pos = 0;
        if( selected.isEmpty() ) {
            if ( this.validateClick(selectedButton) == false ) {
                resetSelection();
            }
            else {
                System.out.println("Got the first letter!");
            }
            
            // Always outline selected button.
            selected.add(selectedButton);
            selectedButton.setStyle("-fx-border-color: green;");
        }
        else if ( selectedButton != selected.get(pos)) {
            if ( this.validateClick(selectedButton) == false ) {
                resetSelection();
                playSound(this.buzzer);
            }
            
            if ( selected.size() > first ) {
                System.out.println("Found word!");
                selected.clear();
            }
        }
    }
    
    /**
     * This method implements the logic for selecting a word from the board.
     * @param button
     * @return 
     */
    private boolean validateClick(Node b) {

        int r = getR(b);
        int c = getC(b);
        
        int num;
        boolean returnValue = false;
        if ( selected.isEmpty() ) {
            for ( int i = 0; i < board.getNumWords(); i++ ) {
                Character compare = (Character) this.wordKey[r][c][i];
                
                if ( compare != null ) {
                    btnKey = i;
                    returnValue = true;
                }
            }
            return returnValue;
        }
        else {
            for ( int i = 0; i < board.getNumWords(); i++ ) {
                Character compare = (Character) this.wordKey[r][c][i];
                
                if ( compare != null ) {
                    num = i;
                    if ( num == this.btnKey ) {
                        selected.add(b);
                        if ( foundWord( (Word)targetKeys[num] ) == true ) {
                            markWord();
                            playSound(correct);
                            selected.clear();
                            resetTimer();
                            if ( this.board.isGameover() == true )
                                this.gameOver();
                            return true;
                        }
                        else
                            return false;
                    }
                }
            }
        }
        return false;
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
     * Gets the row coordinate of the button.
     * @param button
     * @return 
     */
    private int getR(Node b) {
        return GridPane.getRowIndex(b);
    }
    
    /**
     * Gets the column coordinate of the button.
     * @param button
     * @return 
     */
    private int getC(Node b) {
        return GridPane.getColumnIndex(b);
    }

    /**
     * Gets the board.
     * @return
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Checks if the word is found.
     */
    private boolean foundWord(Word word) {
        return board.foundWord(word);
    }
    
    /**
     * Logic executed when the game is over.
     */
    private void gameOver() {
        try {
            this.board.getTimer().cancelTimer();
            this.showGameoverScene(null);
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Plays a sound from the given file.
     * @param file 
     */
    private void playSound(Media sound) {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    
    /**
     * Resets the buttons and associated attributes after a selection.
     */
    private void resetSelection() {
        for ( Node b : selected ) {
            b.setStyle("");
        }
        selected.clear();
    }
    
    /**
     * Resets the timer.
     */
    private void resetTimer() {
        this.board.getTimer().cancelTimer();
        this.board.getTimer().startTimer();
    }
    
    /**
     * Marks found words.
     * @return 
     */
    private void markWord() {
        int first   = 0;
        int second  = 1;
        int firstR  = getR(selected.get(first));
        int firstC  = getC(selected.get(first));
        int secondR = getR(selected.get(second));
        int secondC = getC(selected.get(second));
        int cDiff   = secondC - firstC;
        
        // Get all the buttons between the two and add to list
        // Same row
        if ( firstR == secondR ) {
            int r = firstR;
            if ( cDiff > 0 ) {
                for ( int c = firstC + 1; c < secondC; c++ ) {
                    try {
                        selected.add(this.getGridNode(r, c));
                    } catch (IOException ex) {
                        System.out.println("Unexpected Exeption: " + ex.getMessage());
                    }
                }
            }
            else {
                for ( int c = secondC + 1; c < firstC; c++ ) {
                    try {
                        selected.add(this.getGridNode(r, c));
                    } catch (IOException ex) {
                        System.out.println("Unexpected Exeption: " + ex.getMessage());
                    }
                }
            }
        }
        // First row is less than second row
        else if ( firstR < secondR ) {
            if ( cDiff == 0 ) {
                for ( int r = firstR + 1; r < secondR; r++ ) {
                    int c = firstC;
                    try {
                        selected.add(this.getGridNode(r, c));
                    } catch (IOException ex) {
                        System.out.println("Unexpected Exeption: " + ex.getMessage());
                    }
                }
            }
            else if ( cDiff > 0 ) {
                int r = firstR + 1;
                while ( r < secondR ) {
                    for ( int c = firstC + 1; c < secondC; c++ ) {
                        try {
                            selected.add(this.getGridNode(r, c));
                        } catch (IOException ex) {
                            System.out.println("Unexpected Exeption: " + ex.getMessage());
                        }
                        r++;
                    }
                }
            }
            else {
                int r = firstR + 1;
                while ( r < secondR ) {
                    for ( int c = firstC - 1; c > secondC; c-- ) {
                        try {
                            selected.add(this.getGridNode(r, c));
                        } catch (IOException ex) {
                            System.out.println("Unexpected Exeption: " + ex.getMessage());
                        }
                        r++;
                    }
                }
            }
        }
        // First row is greater than second row 
        else {
            if ( cDiff == 0 ) {
                for ( int r = secondR + 1; r < firstR; r++ ) {
                    int c = firstC;
                    try {
                        selected.add(this.getGridNode(r, c));
                    } catch (IOException ex) {
                        System.out.println("Unexpected Exeption: " + ex.getMessage());
                    }
                }
            }
            else if ( cDiff > 0 ) {
                int r = firstR - 1;
                while ( r > secondR ) {
                    for ( int c = firstC + 1; c < secondC; c++ ) {
                        try {
                            selected.add(this.getGridNode(r, c));
                        } catch (IOException ex) {
                            System.out.println("Unexpected Exeption: " + ex.getMessage());
                        }
                        r--;
                    }
                }
            }
            else {
                int r = firstR - 1;
                while ( r > secondR ) {
                    for ( int c = firstC - 1; c > secondC; c-- ) {
                        try {
                            selected.add(this.getGridNode(r, c));
                        } catch (IOException ex) {
                            System.out.println("Unexpected Exeption: " + ex.getMessage());
                        }
                        r--;
                    }
                }
            }
        }
        
        // Mark all of the buttons from the found word
        for ( Node b : selected ) {
            mark(b);
        }
    }
    
    /**
     * Method for changing the look of a button.
     */
    private void mark(Node b) {
        Button temp = (Button) b;
        temp.setDisable(true);
        temp.setStyle("-fx-background-color: green");
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

}
