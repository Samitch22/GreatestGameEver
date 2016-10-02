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
    
    //private       WordValidator validator;
    //private       Board         board;
    private final Score         score;
    
    /**
     * Constructs a player with a score.
     */
    public Player() {
        score = new Score();
    }
    
    /**
     * Calls to start the game.
     */
//    public void startGame() {
//        board.startGame();
//    }
    
    /**
     *
     * //@param guess
     */
//    public void GuessWord(Word guess){
//        Boolean valid = validator.validateWord(guess);
//        if( valid ) {
//            validator.foundWord();
//        }
//        else
//            validator.wordNotFound(guess);
//    }

//    public void setBoard(Board board) {
//        this.board = board;
//    }
    
//    public WordValidator getValidator() {
//        return validator;
//    }

    /**
     * 
     * @return 
     */
    public Score getScore() {
        return score;
    }
    
    
}
