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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class represents a player's score.
 * @author Mitchell
 * @author Ryan
 * @author Sam
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
    private final Properties defaultScores;
    private final Properties scores;
    private       InputStream in;
    private       OutputStream out;
    private final String scoreProperties = "scores.properties";
    private final String defaultProperties = "/files/defaultScores.properties";
    private final String ZERO = "0";
    
    
    /**
     * Constructs a score holding current score, high score, low score, average 
     * score, and number of games played.
     * @throws java.io.FileNotFoundException
     */
    public Score() throws FileNotFoundException, IOException {
        foundWords = new ArrayList<>();
        currentScore = 0;
        receivedBonus = false;
        // Load default scores
        this.defaultScores = new Properties();
        // Default properties stored inside JAR file
        this.in = getClass().getResourceAsStream(defaultProperties);
        this.defaultScores.load(in);
        in.close();
        
        // Make changes to the stored scores
        this.scores = new Properties(defaultScores);
        try { // Try to read the file
            // Modified score properties stored outside JAR file
            this.in = new FileInputStream(scoreProperties);
        } catch(FileNotFoundException e) { // If file does not exist
            out = new FileOutputStream(scoreProperties);
            // set defaults from defaultScores
            scores.setProperty("highScore", defaultScores.getProperty("highScore"));
            scores.setProperty("lowScore", defaultScores.getProperty("lowScore"));
            scores.setProperty("averageScore", defaultScores.getProperty("averageScore"));
            scores.setProperty("numGames", defaultScores.getProperty("numGames"));
            scores.store(out, null);
            out.close();
            // Get the file now that it is created
            this.in = new FileInputStream(scoreProperties);
        }
        scores.load(in);
        in.close();

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
        propertyHolder = scores.getProperty("highScore");
        if ( propertyHolder == null )
            propertyHolder = this.ZERO;
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
        if ( propertyHolder == null )
            propertyHolder = this.ZERO;
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
        if ( propertyHolder == null )
            propertyHolder = this.ZERO;
        double tempAverageScore = Double.parseDouble(propertyHolder);
        propertyHolder = scores.getProperty("numGames", "0");
        if ( propertyHolder == null )
            propertyHolder = this.ZERO;
        int tempNumGames = Integer.parseInt(propertyHolder);
        numGames = tempNumGames + 1;
        if(tempNumGames == 0) {
            averageScore = currentScore;
        }
        else {
            averageScore = (tempAverageScore*tempNumGames/numGames) + (currentScore/numGames);
        }
        scores.setProperty("averageScore", String.valueOf(averageScore));
        scores.setProperty("numGames", String.valueOf(numGames));
    }
    
    /**
     * Gets the high score
     * @return 
     */
    public int getHighScore() {
        propertyHolder = scores.getProperty("highScore");
        highScore = Integer.valueOf(propertyHolder);
        return highScore;
    }
    
    /**
     * Gets the low score.
     * @return 
     */
    public int getLowScore() {
        propertyHolder = scores.getProperty("lowScore");
        lowScore = Integer.valueOf(propertyHolder);
        return lowScore;
    }
    
    /**
     * Gets the number of games played.
     * @return 
     */
    public int getNumGames() {
        propertyHolder = scores.getProperty("numGames");
        numGames = Integer.valueOf(propertyHolder);
        return numGames;
    }
    
    /**
     * Gets the average score.
     * @return 
     */
    public double getAverageScore() {
        propertyHolder = scores.getProperty("averageScore");
        averageScore = Double.valueOf(propertyHolder);
        return averageScore;
    }
    
    /**
     * Saves the changes made to the Property.
     */
    public void save() {
        try {
            out = new FileOutputStream(scoreProperties);
            scores.store(out, "Saved Scores");
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
}
