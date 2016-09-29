/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 */
public class WordValidator {
    
    private WordBank wordbank;
    /**
     * @todo
     * @return
     */
    public boolean validateWord(Word guess) {
        Word target = wordbank.gettargetWord();
        target.addAttempt();
        return target.equals(guess);
    }
    
    /**
     * @todo
     */
    public void getNextWord() {
        wordbank.foundWord();
    }
}
