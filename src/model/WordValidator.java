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
    
    private final WordBank wordbank;
    
    public WordValidator(WordBank wordBank) {
        this.wordbank = wordBank;
    }
    
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
     * 
     * @param guess
     */
    public void wordNotFound(Word guess) {
        guess.addAttempt();
    }
}
