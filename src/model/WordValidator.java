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
     * @todo
     * @return
     */
    public boolean validateWord(Word guess) {
        Word target = wordbank.getTargetWord();
        return target.equals(guess);
    }
    
    /**
     * @todo
     */
    public void foundWord() {
        wordbank.foundWord();
    }
    
    /**
     * Possibly not needed
     * @param guess
     * @todo
     */
    public void wordNotFound(Word guess) {
        guess.addAttempt();
    }
}
