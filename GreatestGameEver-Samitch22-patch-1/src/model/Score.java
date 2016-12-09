/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class represents a player's score.
 * @author Mitchell
 * @author Ryan
 * @author Sam
 */
public class Score implements Serializable {
    
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
     
    
    /**
     * Constructs a score holding current score, high score, low score, average 
     * score, and number of games played.
     * @throws java.io.FileNotFoundException
     */
    public Score() throws FileNotFoundException, IOException {
        foundWords = new ArrayList<>();
        currentScore = 0;
        receivedBonus = false;
        
    }
    
    /**
     * Calculates the points
     */
    public void calculatePoints() {
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
        this.receivedBonus = true;
    }

    /**
     * Sets each of the scores.
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
        if(hScore > highScore) {
            highScore = hScore;
        }
    }
    
    /**
     * Sets the low score if it changes
     * @param lScore 
     */
    public void setLowScore(int lScore) {
        if(lScore < lowScore) {
            lowScore = lScore;
        }
    }
    
    /**
     * Sets the average score
     * @param aScore 
     */
    public void setAverageScore(int aScore) {
        int tempNumGames = numGames;
        ++numGames;
        if(tempNumGames == 0) {
            averageScore = currentScore;
        }
        else {
            averageScore = (averageScore*tempNumGames/numGames) + (aScore/numGames);
        }
    }
    
    /**
     * Gets the high score
     * @return 
     */
    public int getHighScore() {
        return highScore;
    }
    
    /**
     * Gets the low score.
     * @return 
     */
    public int getLowScore() {
        return lowScore;
    }
    
    /**
     * Gets the number of games played.
     * @return 
     */
    public int getNumGames() {
        return numGames;
    }
    
    /**
     * Gets the average score.
     * @return 
     */
    public double getAverageScore() {
        return averageScore;
    }

}
