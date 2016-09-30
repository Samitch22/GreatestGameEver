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
public final class Board { 
    
    private static final int        boardSize = 14;
    private        final int        rSize;
    private        final int        cSize;
    private        final Object[][] board;
    private        final WordTimer  timer;
    private        final WordBank   wordBank;
    
    /**
     *
     */
    public Board() {
        this.rSize = Board.getBoardSize();
        this.cSize = Board.getBoardSize();
        board = new Character[getrSize()][getcSize()];
        timer = new WordTimer();
        wordBank = new WordBank();
        createBoard();
    }
    
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
        timer.startTimer();
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
     *
     * @return
     */
    public Word getNextTargetWord() {
        return wordBank.getNewTargetWord();
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
