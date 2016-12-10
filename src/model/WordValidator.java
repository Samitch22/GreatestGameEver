/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 * This class implements word validation.
 * @author Mitchell
 * @author Ryan
 */
public class WordValidator implements Serializable {
    
    private final WordBank wordbank;
    
    /**
     * A class for validating words found by the player.
     * @param wordBank
     */
    public WordValidator(WordBank wordBank) {
        this.wordbank = wordBank;
    }
    
    /**
     * This method checks if the guessed word is the current target word.
     * @param guess
     * @return
     */
    public boolean validateWord(Word guess) {
        List<Word> wBank = wordbank.getWordBank();
        return wBank.contains(guess);
    }
    
    /**
     * Logic for checking if a word is found.
     * @param w
     * @return 
     */
    public boolean foundWord(Word w) {
        boolean isFound = false;
        if ( this.wordbank.getWordBank().contains(w) ) {
            this.wordbank.getScore().addFoundWord(w);
            this.wordbank.getWordBank().remove(w);
            if( this.wordbank.getWordBank().isEmpty() ) {
                this.wordbank.gameOver();
                isFound = true;
            }
            else{
                isFound = true;
            }
        }
        return isFound;
    }
}
