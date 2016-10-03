/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * This class implements word validation.
 * @author Mitchell
 * @author Ryan
 */
public class WordValidator {
    
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
        Word target = wordbank.getTargetWord();
        return target.equals(guess);
    }
    
    /**
     * Logic for checking if a word is found.
     * @param w
     * @return 
     */
    public boolean foundWord(Word w) {
        boolean isFound = false;
        if ( this.wordbank.getTargetWord().equals(w) ) {
            int index = this.wordbank.getWordBank().indexOf(this.wordbank.getTargetWord());
            this.wordbank.getScore().addFoundWord(this.wordbank.getTargetWord());
            this.wordbank.getWordBank().remove(index);
            if( this.wordbank.getWordBank().isEmpty() ) {
                this.wordbank.gameOver();
                isFound = true;
            }
            else{
                this.wordbank.getNewTargetWord();   
                isFound = true;
            }
        }
        return isFound;
    }
    
    /**
     * Logic for when a guess is incorrect.
     * @param guess
     */
    public void wordNotFound(Word guess) {
        guess.addAttempt();
    }
}
