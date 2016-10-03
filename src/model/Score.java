/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a player's score.
 * @author Mitchell
 * @author Ryan
 */
public class Score {
    
    private       int        currentScore;
    private final int        highScore;
    private final List<Word> foundWords;
    private final int        pointValue = 100;
    private final String     hsFile;

    /**
     * Constructs a score holding current score and highscore.
     */
    public Score() {
        foundWords = new ArrayList<>();
        currentScore = 0;
        hsFile = "";
        highScore = this.calcHighscore(); // Read the high score. Save high score
    }
    
    /**
     * Calculates the points
     */
    private void calculatePoints() {
        currentScore = 0;
        for ( Word word : foundWords ) {
            currentScore += pointValue/word.getAttempts();
        }
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
     * Gets the current highscore.
     * @return
     */
    public int getHighScore() {
        return highScore;
    }
    
    /**
     * Adds a word to the found word list.
     * @param w
     */
    public void addFoundWord(Word w) {
        foundWords.add(w);
    }
    
    /**
     * Calculates the highscore.
     * @todo
     */
    private int calcHighscore() {
        return 0;
    }
}
