/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Board;
import model.Player;

/**
 *
 * @author Mitchell
 */
public class GameoverController implements Initializable {
    
    private Player player;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label      lblScore;
    @FXML
    private Label      lblHighscore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = Board.getPlayer();
        this.player.getScore().calculatePoints();
        getScore();
        getHighscore();
        save();
    }

    /**
     * Displays the player's score.
     */
    @FXML
    private void getScore() {
        Integer score = player.getScore().getCurrentScore();
        lblScore.setText(score.toString());
    }
    
    /**
     * Displays the current highscore.
     */
    @FXML
    private void getHighscore() {
        Integer highscore = player.getScore().getHighScore();
        lblHighscore.setText(highscore.toString());
    }
    
    /**
     * Saves the scores.
     */
    private void save() {
        player.getScore().save();
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleStart(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
        Parent pane = FXMLLoader.load(getClass().getResource("/view/BoardScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleQuit(ActionEvent event) throws IOException {
        System.out.println("Quitting");
        System.exit(0);
    }
}
