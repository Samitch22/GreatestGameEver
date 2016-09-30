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
public class Player {
    
    private WordValidator validator;
    private Board         board;
    
    /**
     *
     */
    public Player() {
        
    }
    
    /**
     *
     */
    public void startGame() {
        board.startGame();
    }
    
    /**
     *
     * @param guess
     */
    public void GuessWord(Word guess){
        Boolean valid = validator.validateWord(guess);
        if( valid ) {
            validator.getNextWord();
        }
    }
}
