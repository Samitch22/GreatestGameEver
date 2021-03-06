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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Player;

/**
 * Scene for displaying the information after a game ends.
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
    @FXML
    private Label      lblAvgscore;
    @FXML
    private Label      lblLowscore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        player = BoardController.getPlayer();
        this.player.getScore().calculatePoints();
        getScore();
        getHighscore();
        getAvgscore();
        getLowscore();
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
     * Displays the current high score.
     */
    @FXML
    private void getHighscore() {
        Integer highscore = player.getScore().getHighScore();
        lblHighscore.setText(highscore.toString());
    }
    
    /**
     * Displays the current average score.
     */
    @FXML
    private void getAvgscore() {
        Integer avgScore = (int) player.getScore().getAverageScore();
        lblAvgscore.setText(avgScore.toString());
    }
    
    /**
     * Displays the current low score.
     */
    @FXML
    private void getLowscore() {
        Integer lowScore = player.getScore().getLowScore();
        lblLowscore.setText(lowScore.toString());
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleStart(ActionEvent event) throws IOException {
        System.out.println("Starting a new game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startSingleplayer();
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleQuit(ActionEvent event) throws IOException {
        System.out.println("Quitting");
        Platform.exit();
        System.exit(0);
    }
}
