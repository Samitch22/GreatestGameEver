/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 * @author Sam
 */
public final class Board { // extends timertask
    
    private static final int        boardSize = 14;
    private        final int        rSize;
    private        final int        cSize;
    private        final Object[][] board;
    //private        final WordTimer  wordTimer; //New
    
    public Board() {
        this.rSize = Board.getBoardSize();
        this.cSize = Board.getBoardSize();
        board = new Character[getrSize()][getcSize()];
        //wordTimer = new WordTimer(); //New
        createBoard();
    }
//    
//    @Override
//    public void run() { //New
//        //Get New Word Function
//        //wordTimer.startTimer();
//    }
    
    /**
     * @todo
     */
    public void createBoard() {
        for ( int r = 0; r < Board.boardSize; r++ ) {
            for ( int c = 0; c < Board.boardSize; c++ )
            {
                board[r][c] = 'T';
            }
        }
    }
    
    /**
     *
     */
    public void startGame() {
        
    }
    
    /**
     * @todo
     */
    public void loadWordBank() {
        // TODO
    }

    /**
     * Returns the board.
     * @return
     */
    public Object[][] getBoard() {
        return board;
    }

    /**
     *
     * @return
     */
    public static int getBoardSize() {
        return boardSize;
    }

    /**
     * Returns the number of rows on the board.
     * @return
     */
    public int getrSize() {
        return rSize;
    }
    
    /**
     * Returns the number of columns on the board.
     * @return
     */
    public int getcSize() {
        return cSize;
    }
    
    /**
     * @todo
     */
    private int randomPoint() {
        // TODO
        return -1;
    }
    
    /**
     * @todo
     * @return 
     */
    private String randomLetter() {
        // TODO
        return "";
    }
    
    /**
     * @todo
     * @return 
     */
    private boolean randomBackward() {
        // TODO
        return false;
    }
    
    /**
     * @todo
     */
    private void randomDirection() {
        // TODO
    }
}
