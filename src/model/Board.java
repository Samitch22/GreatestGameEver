/*
 * The Greatest Game Ever
 * The Other Guys
 */
package model;

import java.io.IOException;
import java.util.Random;

/**
 * @todo
 * @author Mitchell
 * @author Sam
 */
public final class Board { 
    
    private static final int          boardSize = 14;
    private        final int          rSize;
    private        final int          cSize;
    private        final Object[][]   board;
    private        final WordTimer    timer;
    private        final WordBank     wordBank;
    private        final Player       player;
    private        final Object[][][] words;
    
    /**
     *
     * @param p
     * @throws java.io.IOException
     */
    public Board(Player p) throws IOException {
        this.rSize    = Board.getBoardSize();
        this.cSize    = Board.getBoardSize();
        this.timer    = new WordTimer();
        this.player   = p;
        this.wordBank = new WordBank(p.getScore());
        this.board    = new Character[getrSize()][getcSize()];
        this.words    = new Object[getrSize()][getcSize()][wordBank.getNumWords()];
        createBoard();
    }
    
    /**
     * @throws java.io.IOException
     * @todo
     */
    public void createBoard() throws IOException {
        loadWordBank();
        for ( int r = 0; r < Board.boardSize; r++ ) {
            for ( int c = 0; c < Board.boardSize; c++ )
            {
                board[r][c] = randomLetter();
            }
        }
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        //while ( wordBank.isGameover() != true ){
            //timer.startTimer();
        //}
    }
    
    /**
     * Creates the word bank to be used for the game.
     * @throws java.io.IOException
     */
    public void loadWordBank() throws IOException {
        this.wordBank.createWordBank();
    }

    /**
     * Returns the board.
     * @return
     */
    public Object[][] getBoard() {
        return board;
    }

    /**
     * Gets the size of the board.
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
     * Gets the current target word.
     * @return
     */
    public Word getTargetWord() {
        return wordBank.getTargetWord();
    }
    
    /**
     * Gets the next target word from the word bank.
     * @return
     */
    public Word getNextTargetWord() {
        return wordBank.getNewTargetWord();
    }
    
    /**
     * Gets a random point in the grid.
     * @todo
     */
    private int[] randomPoint() {
        Random col = new Random();
        Random row = new Random();
        int rrow = row.nextInt(this.rSize);
        int rcol = col.nextInt(this.cSize);
        int[] randpoint = new int[1];
        randpoint[0] = rrow; 
        randpoint[1] = rcol;
        return randpoint;
    }
    
    /**
     * Returns a randomly selected alphabetic letter.
     * @return 
     */
    private char randomLetter() {
        Random letter = new Random();
        int numLetters = 26;
        int letterpos = letter.nextInt(numLetters);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char rletter = alpha.charAt(letterpos);
        return rletter;
    }
    
    /**
     * Returns if the word will be stored backward in the grid.
     * @return 
     */
    private boolean randomBackward() {
        Random backward = new Random();
        boolean isbackward = backward.nextBoolean();
        return isbackward;
    }
    
    /**
     * Returns the direction the word will be stored in the grid.
     *  *** Use a switch statement for each case in creation
     */
    private int randomDirection() {
        Random direction = new Random();
        int numDirections = 4;
        int rdirection = direction.nextInt(numDirections);
        return rdirection;
    }
}
