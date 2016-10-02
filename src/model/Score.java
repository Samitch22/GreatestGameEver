/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @todo
 * @author Mitchell
 * @author Ryan
 */
public class Score {
    
    private       int        currentScore;
    private       int        highScore;
    private final List<Word> foundWords;
    private final int        pointValue = 100;
    private final String     hsFile;

    /**
     *
     */
    public Score() {
        foundWords = new ArrayList<>();
        currentScore = 0;
        hsFile = "";
        highScore = 0; // Read the high score. Save high score
    }
    
    /**
     * 
     * @todo
     */
    public void calculatePoints() {
        for ( Word word : foundWords ) {
            currentScore += pointValue/word.getAttempts();
        }
    }
    
    /**
     *
     * @return
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     *
     * @return
     */
    public int getHighScore() {
        return highScore;
    }
    
    /**
     *
     * @param w
     */
    public void addFoundWord(Word w) {
        foundWords.add(w);
    }
    
    /**
     * @todo
     */
    private void calcHighScore() {
        
    }
}
