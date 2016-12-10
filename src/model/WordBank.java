/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * This class implements the concept of a word bank for the word search.
 * @author Mitchell
 * @author Ryan
 */
public class WordBank implements Serializable {
    
    private       List<Word>     wordBank;
    private       List<Word>     distractionBank;
    private       Score          score;
    private final WordValidator  validator;
    private       int            numWords;
    private       int            numDistractions;
    private       boolean        gameover;

    /**
     * Constructs a word bank that contains a list of words from a word list.
     * @param s
     * @throws java.io.IOException
     */
    public WordBank(Score s) throws IOException {
        score = s;
        validator = new WordValidator(this);
        gameover = false;
    }

    /**
     * Implements the necessary steps to create a word bank.
     * @throws IOException
     */
    public void createWordBank() throws IOException {
        WordFactory factory = WordFactory.makeFactory();
        factory.setWordList();
        factory.createWordBank();
        factory.createDistractionBank();
        wordBank = factory.getWordBank();
        distractionBank = factory.getDistractionBank();
        numWords = factory.getNumWords();
        numDistractions = factory.getNumDistractions();
    }
    
    /**
     * Gets the remaining number of words.
     * @return
     */
    public int getRemainingWords() {
        return wordBank.size();
    }
    
    /**
     * This method implements the logic for after a word is found.
     * @param w
     * @return 
     */
    public boolean foundWord(Word w) {
        
        return validator.foundWord(w);
    }
    
    /**
     * Returns the list of target words.
     * @return
     */
    public List<Word> getWordBank() {
        return wordBank;
    }
    
    /**
     * Returns the list of distraction words.
     * @return
     */
    public List<Word> getDistractionBank() {
        return distractionBank;
    }
    
    /**
     * Calls the game over functionality.
     */
    public void gameOver() {
        this.setGameover(true);
    }

    /**
     * Returns if the game is over or not.
     * @return
     */
    public boolean isGameover() {
        return gameover;
    }

    /**
     * Sets if the game is over.
     * @param gameover
     */
    private void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    /**
     * Gets a random word from the list.
     */   
    private Word getRandomWord() {
        Word returnWord = null;
        Random newTarget = new Random();
        if ( ! wordBank.isEmpty() ) {
            returnWord = wordBank.get(newTarget.nextInt(wordBank.size()));
        }
        return returnWord;
    }

    /**
     * Gets the number of words in the word bank.
     * @return
     */
    public int getNumWords() {
        return numWords;
    }
    
    /**
     * Gets the number of words in the distraction bank.
     * @return
     */
    public int getNumDistractions() {
        return numDistractions;
    }

    /**
     * Gets the score associated with a Player.
     * @return
     */
    public Score getScore() {
        return score;
    }

    /**
     * Sets the score from a Player.
     * @param score
     */
    public void setScore(Score score) {
        this.score = score;
    }
    
}
