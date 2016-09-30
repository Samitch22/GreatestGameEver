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
    
    private       WordValidator validator;
    private       Board         board;
    private       Score         score;
    
    /**
     *
     * @param board
     */
    public Player() {
        score = new Score();
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
            validator.foundWord();
        }
        else
            validator.wordNotFound(guess);
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    
    public WordValidator getValidator() {
        return validator;
    }

    public Score getScore() {
        return score;
    }
    
    
}
