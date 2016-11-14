/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class represents a player's score.
 * @author Mitchell
 * @author Ryan
 */
public class Score {
    
    private       int        currentScore;
    private       int        highScore;
    private       int        lowScore;
    private       double     averageScore;
    private       int        numGames;
    private final List<Word> foundWords;
    private final int        pointValue = 1000;
    private final int        baseMulti = 15;
    private final int        bonus = 5000;
    private       boolean    receivedBonus;
    private       String     propertyHolder;
    
    //For scores
    private Properties scores;
    private FileInputStream in;
    private FileOutputStream out;
    
    
    /**
     * Constructs a score holding current score and highscore.
     */
    public Score() throws FileNotFoundException, IOException {
        foundWords = new ArrayList<>();
        currentScore = 0;
        receivedBonus = false;
        this.scores = new Properties();
        this.out = new FileOutputStream("scores.properties");
        this.in = new FileInputStream("scores.properties");
        scores.load(in);
        in.close();
    }
    
    /**
     * Calculates the points
     */
    private void calculatePoints() {
        currentScore = 0;
        for ( Word word : foundWords ) {
            currentScore += pointValue *( baseMulti - word.toString().length() );
        }
        if ( receivedBonus == true ) {
            currentScore += bonus;
        }
        this.setScores(currentScore);
    }
    
    /**
     * Gets the current score achieved.
     * @return
     */
    public int getCurrentScore() {
        this.calculatePoints();
        return currentScore;
    }
    
    /**
     * Adds a word to the found word list.
     * @param w
     */
    public void addFoundWord(Word w) {
        foundWords.add(w);
    }

    /**
     * Adds bonus points to the score.
     */
    public void addBonus() {
        //this.currentScore += this.bonus;
        this.receivedBonus = true;
    }

    /**
     * 
     * @param score 
     */
    public void setScores(int score) {
        this.setHighScore(score);
        this.setLowScore(score);
        this.setAverageScore(score);
    }
    
    /**
     * Sets the high score if it changes
     * @param hScore 
     */
    public void setHighScore(int hScore) {
        propertyHolder = scores.getProperty("highScore");
        int tempHighScore = Integer.parseInt(propertyHolder);
        if(currentScore > tempHighScore) {
            highScore = currentScore;
            scores.setProperty("highScore", String.valueOf(highScore));
        }
    }
    
    /**
     * Sets the low score if it changes
     * @param lScore 
     */
    public void setLowScore(int lScore) {
        propertyHolder = scores.getProperty("lowScore");
        int tempLowScore = Integer.parseInt(propertyHolder);
        if(currentScore < tempLowScore) {
            lowScore = currentScore;
            scores.setProperty("lowScore", String.valueOf(lowScore));
        }
    }
    
    /**
     * Sets the average score
     * @param aScore 
     */
    public void setAverageScore(int aScore) {
        propertyHolder = scores.getProperty("averageScore");
        double tempAverageScore = Double.parseDouble(propertyHolder);
        propertyHolder = scores.getProperty("numGames", "0");
        int tempNumGames = Integer.parseInt(propertyHolder);
        numGames = tempNumGames + 1;
        if(tempNumGames == 0) {
            averageScore = currentScore;
        }
        else {
            averageScore = (tempAverageScore*tempNumGames/numGames) + (averageScore/numGames);
        }
        scores.setProperty("averageScore", String.valueOf(averageScore));
        scores.setProperty("numGames", String.valueOf(numGames));
    }
    
    /**
     * 
     * @return 
     */
    public int getHighScore() {
        propertyHolder = scores.getProperty("highScore");
        highScore = Integer.valueOf(propertyHolder);
        return highScore;
    }
    
    /**
     * 
     * @return 
     */
    public int getLowScore() {
        propertyHolder = scores.getProperty("lowScore");
        lowScore = Integer.valueOf(propertyHolder);
        return lowScore;
    }
    
    /**
     * 
     * @return 
     */
    public int getNumGames() {
        propertyHolder = scores.getProperty("numGames");
        numGames = Integer.valueOf(propertyHolder);
        return numGames;
    }
    
    /**
     * 
     * @return 
     */
    public double getAverageScore() {
        propertyHolder = scores.getProperty("averageScore");
        averageScore = Double.valueOf(propertyHolder);
        return averageScore;
    }
}
