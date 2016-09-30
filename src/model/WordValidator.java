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
    public void getNextWord() {
        wordbank.foundWord();
    }
    
    /**
     * Possibly not needed
     * @todo
     */
    public void wordNotFound() {
        // TODO
    }
}
