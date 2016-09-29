/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 */
public class Player {
    
    private WordValidator validate;
    
    public void startGame() {
        // TODO
    }
    
    public void GuessWord(Word guess){
        Boolean valid = validate.validateWord(guess);
        if(valid = true) {
            validate.getNextWord();
        }
    }
}
