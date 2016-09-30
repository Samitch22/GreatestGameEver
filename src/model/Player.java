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
    
    public void startGame() {
        // TODO
    }
    
    public void GuessWord(Word guess){
        Boolean valid = validator.validateWord(guess);
        if(valid = true) {
            validator.getNextWord();
        }
    }
}
