/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

/**
 * @todo
 * @author Mitchell
 */
public final class Board {
    
    private static final int        boardSize = 2; //14
    private        final int        rSize;
    private        final int        cSize;
    private        final Object[][] board;
    
    public Board() {
        this.rSize = Board.getBoardSize();
        this.cSize = Board.getBoardSize();
        board = new Character[getrSize()][getcSize()];
        createBoard();
    }
    
    /**
     * @todo
     */
    public void createBoard() {
        board[0][0] = 'A';
        board[1][1] = 'B';
       // board[2][2] = 'C';
       // board[2][1] = 'D';
        board[0][1] = 'E';
        //board[0][2] = 'F';
        board[1][0] = 'G';
        //board[1][2] = 'H';
        //board[2][0] = 'I';
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
     * NOT NECESSESSARILY NEEDED
     * @todo
     * @return
     */
    private int findRowSize() {
        // TODO
        return -1;
    }
    
    /**
     * NOT NECESSESSARILY NEEDED
     * @todo
     * @return 
     */
    private int findColSize() {
        // TODO
        return -1;
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
