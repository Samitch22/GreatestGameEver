/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 * @author Ryan
 */
public class WordValidator {
    
    private WordBank wordbank;
    
    /**
     * @param guess
     * @return
     */
    public boolean validateWord(Word guess) {
        Word target = wordbank.getTargetWord();
        return target.equals(guess);
    }
    
    /**
     * 
     */
    public void foundWord() {
        wordbank.foundWord();
    }
    
    /**
     * 
     * @param guess
     */
    public void wordNotFound(Word guess) {
        guess.addAttempt();
    }
}
